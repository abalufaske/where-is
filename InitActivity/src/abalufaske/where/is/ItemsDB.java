package abalufaske.where.is;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;



import abalufaske.where.is.BaseDatos;
import abalufaske.where.is.commonconstants;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;



import static abalufaske.where.is.commonconstants.*;


public class ItemsDB extends BaseDatos{


	   private final String[] listColumns;

	    /**
	     * Constructor
	     * @param con Contexto
	     */
	    public ItemsDB(final Context con) {
	        super(con);
	       
	        	listColumns = new String[] {TABLE_ITEMS_ID,
	        			TABLE_ITEMS_NAME, 
	        			TABLE_ITEMS_LAST,																			  
	           			TABLE_ITEMS_LONGITUDE,
	        			TABLE_ITEMS_LATITUDE
	    			  };
	       
	
	    }
	    
	

	  
	    public Cursor fetch(long idPlace, Context con){
	    	
	        BaseDatos myDbHelper = new BaseDatos(con);

	    	try {
	    		 
		 		myDbHelper.openDataBase();
		 
		 	}catch(SQLException sqle){
		 
		 		throw sqle;
		 
		 	}
	    	

	    	Cursor mCursor = myDbHelper.myDataBase.query(TABLE_ITEMS, listColumns, TABLE_ITEMS_ID + "=" + idPlace, null, null, null, null, null);
	    	
	    	myDbHelper.close();
	    	if (mCursor != null) {
				mCursor.moveToFirst();
			}
			return mCursor;
	    }

	    public Place get(long idPlace, Context con){
	    	Cursor crs = fetch(idPlace, con);       
	        Place place = new Place(crs);
	        crs.close();
	        return place;    	
	    }
	 

    

	    public Cursor fetchAll(Context con, int facultad){
	    			
	    	
	        BaseDatos myDbHelper = new BaseDatos(con);

	  	    	try {
	  	    		 
	  		 		myDbHelper.openDataBase();
	  		 
	  		 	}catch(SQLException sqle){
	  		 
	  		 		throw sqle;
	  		 
	  		 	}
	  	    	Cursor 	mCursor =null;
	  	    	//= myDbHelper.myDataBase.query( TABLE_FACULTY, listColumns,null, null, null, null, null, null);

	  	
	  	    	
	  	    	if (facultad == 0)
	  	    	{
	  	    		
	  	    	}
  	    //	mCursor = myDbHelper.myDataBase.query( TABLE_ITEMS, listColumns,null, null, null, null, TABLE_ITEMS_LAST , null);
	
	   	    	mCursor = myDbHelper.myDataBase.query( TABLE_ITEMS, listColumns,null, null, null, null, TABLE_ITEMS_LAST + " DESC", null);

	  	    
	    	    	
	    	return mCursor;
	    }
	    
	    
	    

	    


	    /**
	     * Crea una lista de lugares a partir de lo que devuelve un cursor. Todos lo métodos get la usan pues 
	     * todos ellos devuelve una lista de lugares a partir de un cursor.
	     * @param crs
	     * @return
	     */
	    private List<Place> listPlaceFromCursor(Cursor crs){
	    	List<Place> mListPlaces = new ArrayList<Place>();
	    	crs.moveToFirst();
	    	while (!crs.isAfterLast()){
	    		Place place = new Place(crs);
	    		mListPlaces.add(place);
	    		crs.moveToNext();
	    	}
	    	crs.close();
	    	return mListPlaces;
	    }

	   
		public List<Place> getAll(Context applicationContext, int facultad) {
			Cursor crs = fetchAll(applicationContext, facultad);
	    	return listPlaceFromCursor(crs);
		}

		
	


	


		
		public boolean setName (final long idItem , final String Name)
		
		{

		    SQLiteDatabase db = getWritableDatabase();
		    ContentValues cv = new ContentValues();
		    cv.put(TABLE_ITEMS_NAME,Name); //These Fields should be your String values of actual column names

		    if(  db.update(TABLE_ITEMS, cv, TABLE_ITEMS_ID +" = "+idItem, null) >0)
		    	  
		    {
		  	  db.close();
		  	  return true;
		    }    
		    
		    db.close();
		      return false;
		}

		public boolean setLat (final long idItem , final String Latitude)
		
		{

		    SQLiteDatabase db = getWritableDatabase();

		    ContentValues cv = new ContentValues();


		  	  cv.put(TABLE_ITEMS_LATITUDE,Latitude); //These Fields should be your String values of actual column names

		    if(  db.update(TABLE_ITEMS, cv, TABLE_ITEMS_ID +" = "+idItem, null) >0)
		    	  
		    {
		  	  db.close();
		  	  return true;
		    }    
		    
		    db.close();
		      return false;
		}

		
		public boolean setLong (final long idItem , final String Longitude)
		
		{

		    SQLiteDatabase db = getWritableDatabase();
		    ContentValues cv = new ContentValues();
		    cv.put(TABLE_ITEMS_LONGITUDE,Longitude); //These Fields should be your String values of actual column names

		    if(  db.update(TABLE_ITEMS, cv, TABLE_ITEMS_ID +" = "+idItem, null) >0)
		    	  
		    {
		  	  db.close();
		  	  return true;
		    }    
		    
		    db.close();
		      return false;
		}

		public boolean setLast (final long idItem, final int Last)
		
		{
			
			
			 SQLiteDatabase db = getWritableDatabase();
			    ContentValues cv = new ContentValues();
			    cv.put(TABLE_ITEMS_LAST, 0); //These Fields should be your String values of actual column names
				    if(  db.update(TABLE_ITEMS, cv, TABLE_ITEMS_LAST +" = "+Last, null) >0)
				    {	
// we remove the "LAST" value 
				    }

				    ContentValues cv2 = new ContentValues();
				    cv2.put(TABLE_ITEMS_LAST, String.valueOf(Last));
			    if(  db.update(TABLE_ITEMS, cv2, TABLE_ITEMS_ID +" = "+idItem, null) >0)
			    	  
			    {

			  	  db.close();
			  	  return true;
			        
			    
				    }
			    
			    db.close();
			  return false;
		}



	}