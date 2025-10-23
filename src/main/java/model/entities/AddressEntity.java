package model.entities;

import javax.persistence.*;

@Entity
@Table(name = "tb_addresses")
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ViaCEP
    @Column(length = 9) // ex: 01001-000
    private String cep;

    private String cidade;
    private String rua;

    @Column(length = 12)
    private String numero; // string p/ suportar "S/N"

    private String complemento;

    @Column(length = 2)
    private String uf;

    // ====== GET/SET ======
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }

    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    public String getRua() { return rua; }
    public void setRua(String rua) { this.rua = rua; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public String getComplemento() { return complemento; }
    public void setComplemento(String complemento) { this.complemento = complemento; }

    public String getUf() { return uf; }
    public void setUf(String uf) { this.uf = uf; }
}
