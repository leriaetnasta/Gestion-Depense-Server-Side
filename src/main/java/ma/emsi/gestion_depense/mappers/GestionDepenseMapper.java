package ma.emsi.gestion_depense.mappers;

import ma.emsi.gestion_depense.Exceptions.ClientNotFoundException;
import ma.emsi.gestion_depense.dtos.*;
import ma.emsi.gestion_depense.entities.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//mapstruct declarer que la signature de la methode et mapstruct va generer le code
@Service
public class GestionDepenseMapper {

    public ClientDTO fromClient(Client client){
        ClientDTO clientDTO = new ClientDTO();
        BeanUtils.copyProperties(client,clientDTO);
        if(!client.getListProjet().isEmpty()){
            List<ProjetDTO> list= client.getListProjet().stream().map(p->fromProjet(p)).collect(Collectors.toList());
        clientDTO.setListProjet(list);
        }
        return clientDTO;
    }
    public Client fromClientDTO(ClientDTO clientDTO){
        Client client=new Client();
        BeanUtils.copyProperties(clientDTO,client);
        if(!clientDTO.getListProjet().isEmpty()){
            List<Projet> list=clientDTO.getListProjet().stream().map(projetDTO -> fromProjetDTO(projetDTO)).collect(Collectors.toList());
            client.setListProjet(list);
        }
        return client;
    }
    public DepenseDTO fromDepense(Depense depense){
        DepenseDTO depenseDTO = new DepenseDTO();
        BeanUtils.copyProperties(depense,depenseDTO);
        return depenseDTO;
    }
    public Depense fromDepenseDTO(DepenseDTO depenseDTO){
        Depense depense=new Depense();
        BeanUtils.copyProperties(depenseDTO,depense);
        depense.setDeplacement(fromDeplacementDTO(depenseDTO.getDeplacement()));
        return depense;
    }
    public DeplacementDTO fromDeplacement(Deplacement deplacement){
        DeplacementDTO deplacementDTO = new DeplacementDTO();
        BeanUtils.copyProperties(deplacement,deplacementDTO);

        return deplacementDTO;
    }
    public Deplacement fromDeplacementDTO(DeplacementDTO deplacementDTO){
        Deplacement deplacement=new Deplacement();
        BeanUtils.copyProperties(deplacementDTO,deplacement);
        /*deplacement.setProjet(fromProjetDTO(deplacementDTO.getProjet()));
        deplacement.setEmploye(fromEmployeDTO(deplacementDTO.getEmploye()));
        if(deplacementDTO.getListdepense().isEmpty()){
            deplacement.setListdepense(deplacementDTO.getListdepense().stream().map(depenseDTO -> fromDepenseDTO(depenseDTO)).collect(Collectors.toList()));

        }{
            deplacement.setListdepense(new ArrayList<Depense>());
        }*/
        return deplacement;
    }

    public EmployeDTO fromEmploye(Employe employe){
        EmployeDTO employeDTO = new EmployeDTO();
        BeanUtils.copyProperties(employe,employeDTO);
        if(employe.getListDeplacement().isEmpty()){
            employeDTO.setListDeplacement(employe.getListDeplacement().stream().map(deplacement -> fromDeplacement(deplacement)).collect(Collectors.toList()));

        }{
            employeDTO.setListDeplacement(new ArrayList<DeplacementDTO>());
        }
        if(employe.getProjet().isEmpty()){
            employeDTO.setProjet(employe.getProjet().stream().map(projet -> fromProjet(projet)).collect(Collectors.toList()));

        }{
            employeDTO.setProjet(new ArrayList<ProjetDTO>());
        }

        return employeDTO;
    }
    public Employe fromEmployeDTO(EmployeDTO employeDTO){
        Employe employe=new Employe();
        BeanUtils.copyProperties(employeDTO,employe);

        if(employeDTO.getListDeplacement().isEmpty()){
            employe.setListDeplacement(employeDTO.getListDeplacement().stream().map(deplacementDTO -> fromDeplacementDTO(deplacementDTO)).collect(Collectors.toList()));

        }{
            employe.setListDeplacement(new ArrayList<Deplacement>());
        }
        if(employeDTO.getProjet().isEmpty()){
            employe.setProjet(employeDTO.getProjet().stream().map(projetDTO -> fromProjetDTO(projetDTO)).collect(Collectors.toList()));

        }{
            employe.setProjet(new ArrayList<Projet>());
        }

        return employe;
    }



    public ProjetDTO fromProjet(Projet projet)  {
        ProjetDTO projetDTO = new ProjetDTO();
        /*
        projetDTO.setListEmploye(new ArrayList<EmployeDTO>());
        projetDTO.setListDeplacement(new ArrayList<DeplacementDTO>());
        BeanUtils.copyProperties(projet,projetDTO);

        if (!projet.getListDeplacement().isEmpty()){
            projetDTO.setListDeplacement(projet.getListDeplacement().stream().map(deplacement -> fromDeplacement(deplacement)).collect(Collectors.toList()));
        }
        if(!projet.getListEmploye().isEmpty()){
            projetDTO.setListEmploye(projet.getListEmploye().stream().map(employe -> fromEmploye(employe)).collect(Collectors.toList()));
        }*/
        projetDTO.setListEmploye(new ArrayList<Employe>());
        projetDTO.setListDeplacement(new ArrayList<Deplacement>());
        BeanUtils.copyProperties(projet,projetDTO);

        if (!projet.getListDeplacement().isEmpty()){
projetDTO.setListDeplacement(projet.getListDeplacement());        }
        if(!projet.getListEmploye().isEmpty()){
projetDTO.setListEmploye(projet.getListEmploye());        }
        return projetDTO;
    }
    public Projet fromProjetDTO(ProjetDTO projetDTO){
        Projet projet=new Projet();
        BeanUtils.copyProperties(projetDTO,projet);

        /*if (!projetDTO.getListDeplacement().isEmpty()){
            projet.setListDeplacement(projetDTO.getListDeplacement().stream().map(deplacementDTO -> fromDeplacementDTO(deplacementDTO)).collect(Collectors.toList()));
        }{
            projet.setListDeplacement(new ArrayList<Deplacement>());
        }
        if(!projetDTO.getListEmploye().isEmpty()){
            projet.setListEmploye(projetDTO.getListEmploye().stream().map(employeDTO -> fromEmployeDTO(employeDTO)).collect(Collectors.toList()));
        }{
            projet.setListEmploye(new ArrayList<Employe>());
        }*/
        return projet;
    }


}
