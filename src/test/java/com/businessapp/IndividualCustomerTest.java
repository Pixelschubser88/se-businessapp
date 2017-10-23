/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.businessapp.model;

import org.junit.Test;
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
      String result=ic1.getFirstName();
        assertTrue(result.equals("Mayer"));
    }

    /**
     * Test of setFirstName method, of class IndividualCustomer.
     */
    @Test
    public void testSetFirstName() {
        IndividualCustomer ic1 = new IndividualCustomer();
        ic1.setFirstName("Mayer");
         String result=ic1.getFirstName();
         assertTrue(result!=null);
         assertTrue(result.equals("Mayer"));

    }
    
}
