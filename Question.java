public class Question{
//Declarar los atributos
   private String imageName;
   private int response;
   
   
   //Definir el método constructor
   
   public Question(String imageName,int response){
      this.imageName=imageName;
      this.response=response;
   
   }//Fin del método constructor
   
   public void setImageName(String imageName){
   
   }//fin del método setImageName
   
   public String getImageName(){
      return imageName;
   }//Fin del método getImageName
   
   public void setResponse(int response){
     this.response=response;
   }//Fin del método setResponse
   
   public int getResponse(){
      return response;
   }//fin del método getResponse
   
   
}//Fin de la clase
