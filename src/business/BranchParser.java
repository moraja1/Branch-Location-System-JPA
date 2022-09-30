package business;

import data.dao.modelsDAO.CoordinatesDAO;
import data.repository.Branch;
import data.repository.Coordinates;
import presentation.model.viewModels.BranchInfo;

public class BranchParser {
    public static BranchInfo toBranchInfo(Branch branch) {
        String id = branch.getId();
        String reference = branch.getReference();
        String address = branch.getAddress();
        double zoning_percentage = branch.getZoning_percentage();
        String coords = branch.getCoords().getId();

        CoordinatesDAO dataDAO = new CoordinatesDAO();
        Coordinates coordinates = dataDAO.getSingleObject(coords);
        coords = new StringBuilder().append(coordinates.getX()).append(", ").append(coordinates.getY()).toString();

        return new BranchInfo(id, reference, address, zoning_percentage, coords);
    }
}
