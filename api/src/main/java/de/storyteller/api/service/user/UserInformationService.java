package de.storyteller.api.service.user;

import java.net.ConnectException;

public interface UserInformationService {
  String idToName(String id) throws ConnectException;
}
