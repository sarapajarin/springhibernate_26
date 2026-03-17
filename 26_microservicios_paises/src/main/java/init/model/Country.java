package init.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Country {
	private String name;
	private String region;
	private String capital;
	private long population;
	private String flag;
}
