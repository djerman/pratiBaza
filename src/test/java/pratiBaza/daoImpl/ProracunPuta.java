package pratiBaza.daoImpl;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class ProracunPuta {
	@Test
	void test() {
		//fail("Not yet implemented but maybe latter");
	    List<Integer> numbers = Arrays.asList(1, 2, 3);
	    assertTrue(numbers.stream().mapToInt(Integer::intValue)
	      .sum() > 5, () -> "Sum should be greater than 5");
	    assertTrue(numbers.stream().mapToInt(Integer::intValue).count() == 3);
	}

}
