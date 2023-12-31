package net.softsociety.secretary.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
 * 가족
 * */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Family {

	private int familyId;
	private String userId;
	private String familyRole;
	
}
