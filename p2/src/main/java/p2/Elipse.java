
package p2 ;

class Elipse
{
    private double semiejeMayor ; 
    private double semiejeMenor;

    /**
     * Constructo de la Elipese
     * @param rx Semieje mayor de la Elipse
     * @param ry Semieje menor de la Elipse
     */
    public Elipse(double rx, double ry)
    {
        this.semiejeMayor = rx ; 
        this.semiejeMenor = ry ; 
    }

    /**
     * Método que te comprueba si el punto pertence a la Elipse
     * @param x Coordenada X del punto a comprobar
     * @param y Coordenada Y del punto a comprobar
     * @param ox Coordenada X del punto Origen de la Elipse
     * @param oy Coordenada Y del punto Origen de la Elipse
     * @return  Si se cumple la desigualdad entonces pertenece a la Elipse y retorna true
     *          Por el contrario no se cumple y retorna false    
     */
    public boolean pertenecePunto(double x , double y , double ox , double oy)
    {
        return (Math.pow((x - ox), 2)/(Math.pow(this.semiejeMayor,2)) + 
                Math.pow((y - oy), 2)/(Math.pow(this.semiejeMenor,2))) <= 1;
    }

    public double getSemiejeMayor() {
        return this.semiejeMayor;
    }

    public double getSemiejeMenor() {
        return this.semiejeMenor;
    }

}