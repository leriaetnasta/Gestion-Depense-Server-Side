package ma.emsi.gestion_depense.mappers;

import ma.emsi.gestion_depense.dtos.*;
import ma.emsi.gestion_depense.entities.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

//mapstruct declarer que la signature de la methode et mapstruct va generer le code
@Service
public class GestionDepenseMapper {

    public ClientDTO fromClient(Client client){
        ClientDTO clientDTO = new ClientDTO();
        BeanUtils.copyProperties(client,clientDTO);
        return clientDTO;
    }
    public Client fromClientDTO(ClientDTO clientDTO){
        Client client=new Client();
        BeanUtils.copyProperties(clientDTO,client);
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
        return deplacement;
    }

    public EmployeDTO fromEmploye(Employe employe){
        EmployeDTO employeDTO = new EmployeDTO();
        BeanUtils.copyProperties(employe,employeDTO);
        return employeDTO;
    }
    public Employe fromEmployeDTO(EmployeDTO employeDTO){
        Employe employe=new Employe();
        BeanUtils.copyProperties(employeDTO,employe);
        return employe;
    }

    public MotifDTO fromMotif(Motif motif){
        MotifDTO motifDTO = new MotifDTO();
        BeanUtils.copyProperties(motif,motifDTO);
        return motifDTO;
    }
    public Motif fromMotifDTO(MotifDTO motifDTO){
        Motif motif=new Motif();
        BeanUtils.copyProperties(motifDTO,motif);
        return motif;
    }

    public ProjetDTO fromProjet(Projet projet){
        ProjetDTO projetDTO = new ProjetDTO();
        BeanUtils.copyProperties(projet,projetDTO);
        return projetDTO;
    }
    public Projet fromProjetDTO(ProjetDTO projetDTO){
        Projet projet=new Projet();
        BeanUtils.copyProperties(projetDTO,projet);
        return projet;
    }


}
