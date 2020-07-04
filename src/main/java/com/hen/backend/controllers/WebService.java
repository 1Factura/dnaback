package com.hen.backend.controllers;

import com.hen.backend.bussines.DnaMutationTest;
import com.hen.backend.models.Dna;
import com.hen.backend.models.Human;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate; 
/**
 *
 * @author Henrry Gómez
 */
@RestController
@RequestMapping("/api/v1") 
public class WebService {
  
	@PostConstruct
	public void init(){ 
	} 
        
        @CrossOrigin(origins="*")
	@RequestMapping(value="/mutation", method=RequestMethod.POST)
	public ResponseEntity<Map<String, String>> hasMutation(@RequestBody @Valid Dna dna, BindingResult bindingResult){
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } 
                DnaMutationTest tester = new DnaMutationTest();
                boolean hasMutation = tester.hasMutationHVD(dna.getSecuence());
               
                
		Map<String, String> resultado = new HashMap<>();
		resultado.put("Result", "Has Mutation: " + hasMutation);
		return hasMutation ? new ResponseEntity<Map<String, String>>(resultado,HttpStatus.OK) : new ResponseEntity<Map<String, String>>(resultado,HttpStatus.FORBIDDEN);
	}
        
        @CrossOrigin(origins="*")
	@RequestMapping(value="/stats", method=RequestMethod.GET)
	public ResponseEntity<Map<String, Double>> statsMutation(HttpServletRequest httpServletRequest){  
                Map<String, Double> resultado = new HashMap<>();
            
                try {
                String link = httpServletRequest.getRequestURL().toString();
                String baseUrl = link.substring(0, link.length()-5);
                
                Logger LOGGER = Logger.getLogger("viewResult");
                
                RestTemplate restTemplate = new RestTemplate();
                ResponseEntity<String> response = restTemplate.getForEntity(baseUrl+"human",String.class);
 
                JSONArray array = new JSONArray(response.getBody().toString());
                
                Logger.getLogger(WebService.class.getName()).log(Level.INFO,array.length()+"<-Count"); 

                int count =  array.length(); 
                int countNoMutation = 0;  
                double ratioNoMutation = 0.0;  

                for (int i = 0; i <  array.length(); i++) {
                    JSONObject human = (JSONObject) array.get(i);
                    if(!human.getBoolean("hasMutation")){
                        ++countNoMutation;
                    } 
                } 
                ratioNoMutation =  countNoMutation/(double)count ;
             
                Logger.getLogger(WebService.class.getName()).log(Level.INFO,ratioNoMutation+"<-Ratio"); 
                Logger.getLogger(WebService.class.getName()).log(Level.INFO,countNoMutation+"<-NoMutation"); 

                resultado.put("count_mutations", (double)count);
                resultado.put("count_no_mutation_ratio", ratioNoMutation );
            
                return  new ResponseEntity<Map<String, Double>>(resultado,HttpStatus.OK);
            } catch (JSONException ex) {
                Logger.getLogger(WebService.class.getName()).log(Level.SEVERE, null, ex); 
                return  new ResponseEntity<Map<String, Double>>(resultado,HttpStatus.FORBIDDEN);
            } 
            
	}
	
}
