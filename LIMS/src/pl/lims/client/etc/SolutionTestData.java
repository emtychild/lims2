package pl.lims.client.etc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SolutionTestData 
{
	private static List<SolutionModel> testData = new ArrayList<SolutionModel>();
	static
	{
		String lorem_blabla = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.\n Integer tincidunt tempus nisi, sed fringilla arcu ullamcorper a. Donec rhoncus sagittis leo, non pharetra neque viverra sit amet. Morbi lacus lorem, facilisis facilisis commodo sit amet, euismod eget elit. Nam sodales mi eu magna tincidunt ut rutrum magna rutrum. Duis enim ipsum, tempor id blandit vel, elementum sagittis turpis. Phasellus et odio sem. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Nulla sodales mauris nec dolor molestie nec tristique diam varius. Nunc magna mi, malesuada sit amet tristique sed, sodales eget enim. Suspendisse adipiscing, sem quis auctor ultricies, urna lorem fermentum massa, vitae blandit felis elit eget mauris. Sed a arcu quam. Nulla a congue eros. Aliquam scelerisque semper tincidunt. Sed a enim erat, sit amet lobortis leo. Donec tincidunt, turpis et luctus commodo, est quam pulvinar tortor, eu ultricies ipsum tortor id magna. Maecenas congue massa eu dolor dapibus feugiat. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Cras mattis erat ut dui pellentesque dapibus. ";
		Collections.addAll(testData, 
			new SolutionModel("hardware,printer,paper",lorem_blabla,"09/06/05 22:18"),
			new SolutionModel("hardware,printer",lorem_blabla,"09/06/05 22:18"),
			new SolutionModel("hardware,printer,toner",lorem_blabla,"09/06/05 22:18"),
			new SolutionModel("hardware,printer,toner",lorem_blabla,"09/06/04 08:20"),
			new SolutionModel("hardware,printer,paper",lorem_blabla,"09/06/03 22:18"),
			new SolutionModel("hardware,printer,paper",lorem_blabla,"09/06/02 00:18"),
			new SolutionModel("hardware,printer,paper",lorem_blabla,"09/04/07 20:18"),
			new SolutionModel("hardware,printer,paper",lorem_blabla,"09/04/05 23:18"),
			new SolutionModel("hardware,printer,paper",lorem_blabla,"09/03/23 21:33"),
			new SolutionModel("hardware,printer,paper",lorem_blabla,"09/03/11 23:43"),
			new SolutionModel("hardware,printer,paper",lorem_blabla,"09/03/10 18:57"),
			new SolutionModel("hardware,printer,paper",lorem_blabla,"09/03/05 15:00"),
			new SolutionModel("hardware,printer,paper",lorem_blabla,"09/02/28 14:27"),
			new SolutionModel("hardware,printer,paper",lorem_blabla,"09/02/22 17:14"),
			new SolutionModel("hardware,printer,paper",lorem_blabla,"09/02/18 19:28"),
			new SolutionModel("hardware,printer,paper",lorem_blabla,"09/02/16 20:28"),
			new SolutionModel("hardware,printer,paper",lorem_blabla,"09/02/15 22:18"));
	}
	public static List<SolutionModel> getSolutions()
	{
		return testData;
	}
}
