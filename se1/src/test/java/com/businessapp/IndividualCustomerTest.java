/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.businessapp;

import com.businessapp.model.IndividualCustomer;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.*;

/**
 *
 * @author thiml
 */
public class IndividualCustomerTest {

    public IndividualCustomerTest() {
    }

    /**
     * Test of getFirstName method, of class IndividualCustomer.
     */
    @Test
    public void testGetFirstName() {
        IndividualCustomer ic1 = new IndividualCustomer();

        ic1.setFirstName("Mayer");
        String result = ic1.getFirstName();
        assertTrue(result.equals("Mayer"));

        ic1.setFirstName(null);
        String resultNull = ic1.getFirstName();
        assertNotNull(resultNull == null);

        ic1.setFirstName("");
        String result2 = ic1.getFirstName();
        assertTrue(result2.isEmpty());
    }

    /**
     * Test of setFirstName method, of class IndividualCustomer.
     */


    //---------------------------------------------
    @Test
    
    public void testGetId() {
        IndividualCustomer ic1 = new IndividualCustomer();

        ic1.setId("1234");
        assertTrue(ic1.getId().equals("1234"));

        ic1.setId(null);
        assertTrue(ic1.getId() == null);

        ic1.setId("");
        assertTrue(ic1.getId().isEmpty());
    }


    //-----------------------------------------------------
//    @Test
//    public void setGetCreated() throws Exception {
//        IndividualCustomer ic1 = new IndividualCustomer();
//        DateFormat format = new SimpleDateFormat("dd.mm.yyyy", Locale.GERMAN);
//        Date date = format.parse("03.11.2017");
//        
//        ic1.setCreated(date);
//        assertEquals(ic1.getCreated().equals(date));
//
//     //   ic1.setCreated(null);
//       // assertNull(ic1.getCreated());
//    }
    
   @Test
    public void testGetCreated() throws Exception {
  IndividualCustomer ic1 = new IndividualCustomer();
        DateFormat format = new SimpleDateFormat("dd.mm.yyyy", Locale.GERMAN);
        Date date = format.parse("03.11.2017");

        ic1.setCreated(date);
        System.out.println(ic1.getCreated());
        assertThat(ic1.getCreated()==date, is (true));

       ic1.setCreated((Date)null);
        assertNull(ic1.getCreated());
    }

    //---------------------------------------------
    @Test
    public void testGetName() {
        IndividualCustomer ic1 = new IndividualCustomer();

        ic1.setName("Mayer");
        String result = ic1.getName();
        assertTrue(result.equals("Mayer"));

        ic1.setName(null);
        String resultNull = ic1.getName();
        assertNotNull(resultNull == null);

        ic1.setName("");
        String result2 = ic1.getName();
        assertTrue(result2.isEmpty());
    }

    /**
     * Test of setFirstName method, of class IndividualCustomer.
     */
   

    private void assertEquals(boolean equals) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
