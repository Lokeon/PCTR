
package p2 ; 


class Complejos
{
    private double real_; 
    private double imag_ ; 


    
    /**
     * Constructor de Complejos por defecto
     */
    public Complejos()
    {
        this.real_ = 0.0 ; 
        this.imag_ = 0.0 ;
    }
    /**
     *  Constructor de Complejos
     * @param r parte real del complejo
     * @param i parte imaginaria del complejo
     */
    public Complejos(double r , double i)
    {
        this.real_ = r ; 
        this.imag_ = i ;
    }

    public double getReal_() {
        return this.real_;
    }

    public double getImag_() {
        return this.imag_;
    }

    /**
     * Calcula la suma entre dos complejos
     * @param A Complejo 
     * @return retorna otro complejo con la suma realizada 
     */
    public Complejos suma(Complejos A)
    {
        return new Complejos(this.real_ + A.real_, this.imag_ + A.real_);
    }

    /**
     * Calcula la resta entre dos complejos
     * @param A Complejo
     * @return retorna otro complejo con la resta realizada
     */
    public Complejos resta(Complejos A)
    {
        return new Complejos(this.real_ - A.real_, this.imag_ - A.real_);
    } 

    /**
     * Calcula el modulo de un complejo
     * @return retorna el modulo del complejo realizado
     */
    public double modulo()
    {
        return Math.hypot(this.real_, this.imag_); 
    }

    /**
     * Calcula los productos de dos complejos
     * @param A Complejo 
     * @return retorna otro complejo con el producto realizado
     */
    public Complejos producto(Complejos A)
    {
        return new Complejos(this.real_ * A.real_ - this.imag_ * A.imag_,
                             this.real_ * A.imag_ + this.imag_ * A.real_);
    }

    /**
     * Calcula el cociente entre dos complejos 
     * @param A Complejo
     * @return retorna otro complejo con el cociente realizado
     */
    public Complejos cociente(Complejos A)
    {
        return new Complejos((this.real_* A.real_ + this.imag_ * A.imag_)/
                            (Math.pow(A.real_,2) + Math.pow(A.imag_,2)),
                            (this.imag_* A.real_ - this.real_ * A.imag_)/
                            (Math.pow(A.real_,2) + Math.pow(A.imag_,2)));
    }

    @Override
    public String toString() {
        
     if(imag_ == 0) { return getReal_() + "" ;}
     if(real_ == 0) { return getImag_() + "i";}
     if(imag_ < 0 ) { return getReal_() + " - " + (- getImag_()) + "i" ;}
     return getReal_() + " + " + getImag_() + "i" ;
    }


}