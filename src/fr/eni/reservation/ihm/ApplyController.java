package fr.eni.reservation.ihm;

import java.util.List;

import fr.eni.papeterie.bll.BLLException;
import fr.eni.papeterie.bll.CatalogueManager;
import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;
import fr.eni.papeterie.dal.ArticleDAO;
import fr.eni.papeterie.dal.DALException;
import fr.eni.papeterie.dal.DAOFactory;

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

