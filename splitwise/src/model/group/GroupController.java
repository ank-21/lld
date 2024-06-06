package model.group;

import java.util.ArrayList;
import java.util.List;

public class GroupController {
    List<Group> groupList;

    public GroupController() {
        this.groupList = new ArrayList<>();
    }

    public void addGroup(Group group){
        groupList.add(group);
    }

    public Group getGroup(String groupId){
        for(Group group : groupList){
            if(groupId.equals(group.getGroupId())){
                return group;
            }
        }
        return null;
    }

    public List<Group> getGroupList() {
        return groupList;
    }
}
