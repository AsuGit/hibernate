package com.pr.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.pr.entity.Product;

public class SaveObjectTest {

	public static void main(String[] args) {
		Configuration cfg=null;
		SessionFactory factory=null;
		Session ses=null;
		Product prod=null;
		Transaction tx=null;
		boolean flag=false;
		int idval=0;
		//Activate hibernate
		cfg=new Configuration();
		//give hibernate cfg file as input
		cfg.configure("com/pr/cfg/hibernate.cfg.xml");
		//build session factory
         factory=cfg.buildSessionFactory();
         //create session
         ses=factory.openSession();
         //create entity obj and save with DB
         prod=new Product();
         prod.setPid(140);
         prod.setPname("Pant");
         prod.setPrice(1800);
         prod.setQty(2);
         try {
        	 tx=ses.beginTransaction();
        	 //save obj
        	idval=(int) ses.save(prod);
        	System.out.println("generated id val:"+idval);
        	 flag=true;
         }//try
         catch(HibernateException hbe) {
        	 hbe.printStackTrace();
        	 flag=false;
         }//catch
         finally {
        	 //commit or rollback tx
        	 if(flag==true) {
        		 tx.commit();
        		 System.out.println("object is saved");
        	 }
        	 else {
        		 tx.rollback();
        		 System.out.println("object is not saved");
        	 }
        	 //close session
        	 ses.close();
        	 //close session factory
        	 factory.close();
         }//finally
	}//main

}//class
