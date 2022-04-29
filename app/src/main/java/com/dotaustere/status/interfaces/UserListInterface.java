package com.dotaustere.status.interfaces;


import com.dotaustere.status.model.FBStoryModel.NodeModel;
import com.dotaustere.status.model.story.TrayModel;

public interface UserListInterface {
    void userListClick(int position, TrayModel trayModel);
    void fbUserListClick(int position, NodeModel trayModel);
}
