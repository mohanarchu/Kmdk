package com.video.aashi.kmdk.Locations;

import com.video.aashi.kmdk.Locations.branch.BranchList;
import com.video.aashi.kmdk.Locations.district.DistrictList;
import com.video.aashi.kmdk.Locations.states.StateList;
import com.video.aashi.kmdk.Locations.zone.ZoneList;

import java.util.ArrayList;

public interface StatePresenter {

    void getStateList(ArrayList<StateList> stateLists);
    void getDistrict(ArrayList<DistrictList> districtLists);
    void getZOnes(ArrayList<ZoneList> zoneLists);
    void getBranches(ArrayList<BranchList> branchLists);
    void showProgressView();
    void hideProgress();
    void showProgressMessage(String string);
    void showMessage(String string);
}
