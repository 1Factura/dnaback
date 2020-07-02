# Detector de Mutación en cadena de adn - Api 
=========================================
 
[![Appveyor Build Status](https://ci.appveyor.com/api/projects/status/1ys5ot401m0fep6l/branch/master?svg=true)](https://ci.appveyor.com/project/guolinke/lightgbm/branch/master)

[Demo Api](http://dnatester-env.eba-kiffzd8h.us-east-1.elasticbeanstalk.com/api/v1/stats)! 
 
La api expone "/mutation/" metodo Post que recibe el siguiente objeto json para su analisis y detectar si tiene mutación: 
{
"dna":["ABCT","ACTB","ATCB","TBDB","GBDB"]
}


## Capturas de Pantalla

Si la cadena de adn tiene mutación, la petición devuelve estatus 200.
<p align="center">
  <img alt="Mutation" src="https://firebasestorage.googleapis.com/v0/b/chibi-mechas-vs-kaijus.appspot.com/o/Api2.png?alt=media&token=8ca1d3a9-489f-4a2c-847e-90531f524e8a">
</p>

Si la cadena de adn no tiene mutación, la petición devuelve estatus 403.
<p align="center">
  <img alt="NoMutation" src="https://firebasestorage.googleapis.com/v0/b/chibi-mechas-vs-kaijus.appspot.com/o/Api3.png?alt=media&token=d56b29e7-4804-479a-b16d-3ff9cc020b3e">
</p>


Estatus te devuelve el total de analisis y el porcentaje de no mutados.
<p align="center">
  <img alt="Estatus" src="https://firebasestorage.googleapis.com/v0/b/chibi-mechas-vs-kaijus.appspot.com/o/Api1.png?alt=media&token=daa8b110-3277-4831-8d45-b4971ea5be30">
</p>