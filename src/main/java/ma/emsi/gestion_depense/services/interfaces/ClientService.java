package ma.emsi.gestion_depense.services.interfaces;

import ma.emsi.gestion_depense.Exceptions.ClientNotFoundException;
import ma.emsi.gestion_depense.dtos.ClientDTO;

import java.util.List;

public interface ClientService {
    List<ClientDTO> listClient();

    ClientDTO getClient(int i) throws ClientNotFoundException;

    ClientDTO saveClient(ClientDTO clientDTO) throws ClientNotFoundException;

    ClientDTO updateClient(ClientDTO clientDTO);

    void deleteClient(int id);

    List<ClientDTO> chercherClient(String keyword);
}
