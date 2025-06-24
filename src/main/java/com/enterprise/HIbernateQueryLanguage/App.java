package com.enterprise.HIbernateQueryLanguage;

import java.util.List;
import java.util.Random;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.*;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        
    	Configuration con = new Configuration().configure().addAnnotatedClass(Student.class);
    	SessionFactory sf = con.buildSessionFactory();
    	Session session = sf.openSession();
    	
    	/*String[] names = {
    		    "Alice", "Bob", "Charlie", "Diana", "Ethan", "Fiona", "George", "Hannah", "Isaac", "Julia",
    		    "Kevin", "Laura", "Michael", "Nina", "Oscar", "Paula", "Quentin", "Rachel", "Samuel", "Tina",
    		    "Uma", "Victor", "Wendy", "Xander", "Yasmine", "Zach", "Ava", "Brian", "Chloe", "David",
    		    "Ella", "Frank", "Grace", "Henry", "Ivy", "Jack", "Katie", "Liam", "Mia", "Noah",
    		    "Olivia", "Peter", "Queen", "Ron", "Sophia", "Tom", "Ursula", "Violet", "Will", "Zoe"
    		};*/
    	
    	
    	Transaction tx = session.beginTransaction();
    	/*
    	 for(int i = 0;i<50;i++) {
    		Student s = new Student();
    		s.setMarks(random.nextInt(100));
    		s.setName(names[i]);
    		s.setRollNo(i+1);
    		session.persist(s);
    	}
    	 */
    	Query q1 = session.createQuery("from Student where marks > 50",Student.class);
    	List<Student> list = q1.getResultList();
    	for(Student s : list) {
    		
    		System.out.println(s);
    	}
    	Query q2 = session.createQuery("from Student where rollNo = 50");
    	Student s1 = (Student) q2.getSingleResult();
    	System.out.println(s1);
    	
    	//Special case native sql queries in hql
    	Query q3 = session.createQuery("select rollNo,name,marks from Student where rollNo = 46");
    	Object[] student = (Object[]) q3.getSingleResult(); // Array of Objects (Integer ,String)
    	System.out.println(student[0] + " " + student[1] + " " + student[2]);
    	
    	int marks = 80;
    	Query q4 = session.createQuery("select rollNo,name,marks from Student where marks > :marks");
    	//q4.setParameter("marks",marks);
    	q4.setParameter("marks", marks);
    	List<Object[]> students =  q4.getResultList();
    	for(Object[] s : students) {
    		System.out.println(s[0] + " " + s[1] + " " + s[2]);
    	}
    	// we can also perform aggregate functions
    	// also preparedStatements with sligh change in syntax
    	System.out.println("Aggregate functions :");
    	Query q5 = session.createQuery("select sum(marks) from Student");
    	long sum = (Long) q5.getSingleResult();
    	System.out.println(sum);
    	tx.commit();
    	session.close();
    	
    	
    	
    }
}
