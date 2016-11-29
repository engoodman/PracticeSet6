package base;
import java.util.ArrayList;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


import domain.PersonDomainModel;
import domain.StudentDomainModel;
import util.HibernateUtil;


public class PersonDAL {
	public static PersonDomainModel addPerson(PersonDomainModel PersonID){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		int employeeID = 0;
		try {
			tx = session.beginTransaction();
			session.save(PersonID);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return PersonID;
	}
	public static ArrayList<StudentDomainModel> getPerson() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		PersonDomainModel PersonGet = null;		
		ArrayList<StudentDomainModel> Person = new ArrayList<PersonDomainModel>();
		
		try {
			tx = session.beginTransaction();	
			
			List person = session.createQuery("FROM PersonDomainModel").list();
			for (Iterator iterator = person.iterator(); iterator.hasNext();) {
				PersonDomainModel PersonID = (PersonDomainModel) iterator.next();
				person.add(PersonID);

			}
			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return person;
	}		
	
	
	public static PersonDomainModel getAllPersons(UUID PersonID) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		PersonDomainModel PersonGet = null;		
		
		try {
			tx = session.beginTransaction();	
									
			Query query = session.createQuery("from PersonDomainModel where PersonId = :id ");
			query.setParameter("id", PersonID.toString());
			
			List<?> list = query.list();
			PersonGet = (PersonDomainModel)list.get(0);
			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return PersonGet;
	}
	
	public static void deletePerson(UUID PersonID) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		PersonDomainModel PersonGet = null;		
		
		try {
			tx = session.beginTransaction();	
									
			PersonDomainModel PersonID = (PersonDomainModel) session.get(PersonDomainModel.class, PersonID);
			session.delete(PersonID);
		
			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}	
	

	public static PersonDomainModel updatePerson(PersonDomainModel PersonID) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		PersonDomainModel PersonGet = null;		
		
		try {
			tx = session.beginTransaction();	
									
			session.update(PersonID);
	
			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return PersonID;
	}	
	
	

}




