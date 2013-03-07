package com.github.mineGeek.Reset.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilities {

	public static List<Integer> getSecondsFromText( List<String> list ) {
		List<Integer> result = new ArrayList<Integer>();
		for ( String x : list ) { result.add( getSecondsFromText( x ) ); }
		return result;
	}
	
	public static Integer getSecondsFromText( String value ) {
		
		
		if ( value == null ) return 0;
		
		try {
			Matcher match = Pattern.compile("(?:(-?))?(?:(\\d+)d)?(?:(\\d+)h)?(?:(\\d+)m)?(?:(\\d+)s)?").matcher( value );
			Integer secs = 0;	
			boolean negative = false;
			
			if ( match.find() ) {

				if ( match.group(1).equals( "-")  ) negative = true;
				if ( match.group(2) != null ) secs = 	(60*60*24 * Integer.parseInt( match.group(2) ));
				if ( match.group(3) != null ) secs += 	(60*60* Integer.parseInt( match.group(3) ) );
				if ( match.group(4) != null ) secs += 	(60* Integer.parseInt( match.group(4) ) );
				if ( match.group(5) != null ) secs += 	(Integer.parseInt( match.group(5) ) );
				
			}
		
			return ( negative ? secs * -1 : secs );
			
		} catch ( Exception e ) {}
		
		return null;
		
	}	
}
