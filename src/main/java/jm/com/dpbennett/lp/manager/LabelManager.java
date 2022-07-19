/*
LabelPrint 
Copyright (C) 2022  D P Bennett & Associates Limited

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.

Email: info@dpbennett.com.jm
 */
package jm.com.dpbennett.lp.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import jm.com.dpbennett.business.entity.hrm.User;
import jm.com.dpbennett.business.entity.mt.EnergyLabel;
import jm.com.dpbennett.business.entity.util.BusinessEntityUtils;
import jm.com.dpbennett.cm.manager.ClientManager;
import jm.com.dpbennett.sm.manager.SystemManager;
import jm.com.dpbennett.sm.util.BeanUtils;
import jm.com.dpbennett.sm.util.MainTabView;
import jm.com.dpbennett.sm.Authentication.AuthenticationListener;
import jm.com.dpbennett.sm.util.PrimeFacesUtils;
import org.primefaces.event.CellEditEvent;

/**
 *
 * @author Desmond Bennett
 */
public class LabelManager implements Serializable, AuthenticationListener {

    private String searchText;
    private List<EnergyLabel> foundEnergyLabels;
    private EnergyLabel selectedEnergyLabel;

    /**
     * Creates a new instance of LabelManager
     */
    public LabelManager() {
        init();
    }
    
    public void okLabel() {
        
    }
    
    public void cancelLabelEdit() {
        
    }

    public void updateEnergyLabel() {

    }

    public EnergyLabel getSelectedEnergyLabel() {
        return selectedEnergyLabel;
    }

    public void setSelectedEnergyLabel(EnergyLabel selectedEnergyLabel) {
        this.selectedEnergyLabel = selectedEnergyLabel;
    }

    public void editSelectedEnergyLabel() {

        PrimeFacesUtils.openDialog(null, "labelDialog", true, true, true, 550, 700);
    }

    public void createNewEnergyLabel() {

    }

    public String getApplicationHeader() {

        return "LabelPrint";
    }

    /**
     * Gets the SystemManager object as a session bean.
     *
     * @return
     */
    public SystemManager getSystemManager() {
        return BeanUtils.findBean("systemManager");
    }

    public MainTabView getMainTabView() {
        return getSystemManager().getMainTabView();
    }

    private void init() {
        reset();

        getSystemManager().addSingleAuthenticationListener(this);

    }

    private void initManagers() {
        try {

            getClientManager();

        } catch (Exception e) {
            System.out.println("An error occured while resetting managers: " + e);
        }
    }

    /**
     * Gets the ClientManager SessionScoped bean.
     *
     * @return
     */
    public ClientManager getClientManager() {

        return BeanUtils.findBean("clientManager");
    }

    public void reset() {
        searchText = "";
        //foundEnergyLabels = new ArrayList<>();

        initManagers();
    }

    // tk
    public List<EnergyLabel> findLabels(String searchField,
            String searchPattern) {

        List<EnergyLabel> labelsFound;

        String query = "SELECT r FROM EnergyLabel r WHERE r." + searchField + " LIKE '%" + searchPattern + "%'";

        try {
            labelsFound = (List<EnergyLabel>) getEntityManager1().createQuery(query).getResultList();
        } catch (Exception e) {
            System.out.println(e);

            labelsFound = new ArrayList<>();
        }

        return labelsFound;
    }

    public List<EnergyLabel> getFoundEnergyLabels() {
        if (foundEnergyLabels == null) {
            foundEnergyLabels = findLabels("model", "");
        }
        return foundEnergyLabels;
    }

    public void setFoundEnergyLabels(List<EnergyLabel> foundEnergyLabels) {
        this.foundEnergyLabels = foundEnergyLabels;
    }

    public void onLabelCellEdit(CellEditEvent event) {
        BusinessEntityUtils.saveBusinessEntityInTransaction(getEntityManager1(),
                getFoundEnergyLabels().get(event.getRowIndex()));
    }

    public int getNumLabelsFound() {
        return getFoundEnergyLabels().size();
    }

    public void doEnergyLabelSearch() {
//        if (searchText.trim().length() > 1) {
//            if (getIsActiveClientsOnly()) {
//                foundClients = Client.findActiveClientsByAnyPartOfName(getEntityManager1(), searchText);
//            } else {
//                foundClients = Client.findClientsByAnyPartOfName(getEntityManager1(), searchText);
//            }
//        } else {
//            foundClients = new ArrayList<>();
//        }
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public User getUser() {
        return getSystemManager().getUser();
    }

    public EntityManager getEntityManager1() {

        return getSystemManager().getEntityManager();

    }

    public EntityManager getEntityManager2() {

        return getSystemManager().getEntityManager2();

    }

    public void doDefaultSearch() {

        switch (getSystemManager().getDashboard().getSelectedTabId()) {
            case "Energy Labels":
                break;
            default:
                break;
        }
    }

    private void initDashboard() {
    }

    private void initMainTabView() {
    }

    @Override
    public void completeLogin() {
        initDashboard();
        initMainTabView();
    }

    @Override
    public void completeLogout() {
        reset();
    }

}
