/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.core.dao.models.Ficha;
import com.core.services.FichaService;
import com.core.services.UsuarioService;
import com.core.util.Conexion;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Usuario
 */
public class PostgreSQLTest {
    
    public PostgreSQLTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     @Ignore
     public void parseTest() {
         Date now = new Date();
         Timestamp time = new Timestamp((now).getTime());
//         System.out.println(time);
         
        UsuarioService us = new UsuarioService();
        us.login("yogonza524", "123");
     }
     
     @Test
     @Ignore
     public void fichaTest(){
         FichaService fs = new FichaService();
         
         Ficha f = new Ficha();
         f.setBruxismo(true);
         f.setCantidadDePiezasExistentes(32);
         f.setFuma(true);
         f.setCuantosFuma("Unos cuantos");
         
         if (fs.add(f)) {
             System.out.println("Agregado");
         }
         else{
             System.out.println("No agregado");
         }
     }
     
     @Test
     public void updateTest(){
        try {
            int u = Conexion.getInstancia().actualizar("UPDATE pacientes p SET observaciones = 'copete' WHERE p.id = 'a526fd47-8912-4f27-9011d-dc15fa33'");
            System.out.println(u);
        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLTest.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
}
