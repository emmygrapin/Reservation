package fr.eni.reservation.ihm;

import java.util.List;

public class ApplyController {
	
	public static ApplyController instance;
	
	private reservation ecr;
	private int panel = 0;
	
	
	
	private ApplyController()
	{
		ecr = new reservation();
	}
	
	public static ApplyController getInstance()
	{
		if( ApplyController.instance == null)
		{
			ApplyController.instance = new ApplyController();
		}
		return ApplyController.instance;
	}

}

