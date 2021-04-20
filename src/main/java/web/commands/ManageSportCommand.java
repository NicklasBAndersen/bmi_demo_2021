package web.commands;

import business.exceptions.UserException;
import business.services.BmiFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ManageSportCommand extends CommandProtectedPage{

    BmiFacade bmiFacade;

    public ManageSportCommand(String pageToShow, String role) {
        super(pageToShow, role);
        this.bmiFacade = new BmiFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {

        String deleteId = request.getParameter("delete");
        if(deleteId != null){
            int rowsAffected = bmiFacade.deleteSport(Integer.parseInt(deleteId));
            if(rowsAffected > 0){
                //update sportList in application scope
                request.getServletContext().setAttribute("sportList", bmiFacade.getAllSports());
            }else{
                request.setAttribute("error", "Du kan ikke fjerne denne sportsgren, da den er valgt af andre brugere");
            }

        }

        return pageToShow;
    }
}
