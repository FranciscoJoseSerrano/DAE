
package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author joaos
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "getAllProfissionais",
            query = "SELECT s FROM ProfissionalSaude s ORDER BY s.nome")})
public class ProfissionalSaude extends Utilizador implements Serializable {
    
    public ProfissionalSaude() {
    }

    public ProfissionalSaude(String username, String name, String password) {
        super(username,password,name,GROUP.ProfissionalSaude);
    }
}
