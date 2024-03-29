package modelo;

import decorators.DecoratorCirugia;
import decorators.DecoratorClinica;
import decorators.DecoratorDoctorado;
import decorators.DecoratorMagister;
import decorators.DecoratorPediatria;
import decorators.DecoratorPermanente;
import decorators.DecoratorResidente;
import decorators.IMedico;
import excepciones.DatosInvalidosMedicoException;

/**
 * @author Imanol V�zquez,Juan Manuel Mujica,Francisco Stimmler,Sebastian Ag�ero   
 *<br>
 *Clase que se encarga de la creaci�n de m�dicos mediante la utilizaci�n del Patr�n Factory.
 */
public class MedicoFactory {
	/**
     *Metodo est�tico que genera una instancia de IMedico y lo decora de acuerdo a los par�metros recibidos.<br>
     *<b>Pre: </b> Los par�metros de tipo String deben ser distintos de null y no deben estar vacios.
     *nroMatricula debe ser entero positivo.<br>
     *<b>Post: </b> Se crea una instancia decorada de IMedico.<br>
     *@param especialidad : par�metro de tipo String que representa la especialidad del m�dico a crear. 
     *@param contratacion : par�metro de tipo String que representa el tipo de contrataci�n del m�dico a crear. 
     *@param posgrado : par�metro de tipo String que representa el tipo de posgrado del m�dico a crear.
     *@param dni : par�metro de tipo String que representa el dni del m�dico a crear.
     *@param nombre : par�metro de tipo String que representa el nombre del m�dico a crear.
     *@param apellido : par�metro de tipo String que representa el apellido del m�dico a crear.
     *@param domicilio : par�metro de tipo String que representa el domicilio del m�dico a crear.
     *@param ciudad : par�metro de tipo String que representa la ciudad del m�dico a crear.
     *@param telefono : par�metro de tipo String que representa el n�mero de tel�fono del m�dico a crear.
     *@param nroMatricula : par�metro de tipo long que representa n�mero de matricula particular del m�dico a crear.
     *@return IMedico con todos sus atributos y decoradores correspondientes indicados.
     */
	public static IMedico getMedico(String especialidad,String contratacion,String posgrado,String dni, String nombre, String apellido, String domicilio, String ciudad, String telefono,long nroMatricula) throws DatosInvalidosMedicoException {

		IMedico encapsulado = null;
		if(especialidad.equals("Clinica")) {
			encapsulado = new MedicoClinico(dni, nombre, apellido, domicilio, ciudad, telefono, nroMatricula);
		}else if(especialidad.equals("Cirugia")) {
			encapsulado = new MedicoCirujano(dni, nombre, apellido, domicilio, ciudad, telefono, nroMatricula);
		}else if(especialidad.equals("Pediatria")) {
			encapsulado = new MedicoPediatra(dni, nombre, apellido, domicilio, ciudad, telefono, nroMatricula);
		}else {
			throw new DatosInvalidosMedicoException("Especialidad inv�lida.");
		}
		//IMedico encapsulado = new Medico(dni, nombre, apellido, domicilio, ciudad, telefono, nroMatricula);
		IMedico respuesta = null;

		/*if(especialidad.equals("Clinica")) {
			respuesta = new DecoratorClinica(encapsulado);
		}else if(especialidad.equals("Cirugia")) {
			respuesta = new DecoratorCirugia(encapsulado);
		}else if(especialidad.equals("Pediatria")) {
			respuesta = new DecoratorPediatria(encapsulado);
		}else {
			throw new DatosInvalidosMedicoException("Especialidad inv�lida.");
		}*/

		if(posgrado.equals("Doctorado")) {
			respuesta = new DecoratorDoctorado(encapsulado);
		}else if(posgrado.equals("Magister")) {
			respuesta = new DecoratorMagister(encapsulado);
		}else {
			throw new DatosInvalidosMedicoException("Doctorado inv�lido.");
		}

		if(contratacion.equals("Permanente")) {
			respuesta = new DecoratorPermanente(respuesta);
		}else if(contratacion.equals("Residente")) {
			respuesta = new DecoratorResidente(respuesta);
		}else {
			throw new DatosInvalidosMedicoException("Contrataci�n inv�lida.");
		}

		return respuesta;
	}
}
