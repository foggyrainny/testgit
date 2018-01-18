package cn.goodjobs.cms.entity;

import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import raky.ssh.entity.CoreEntity;
@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name="users")
public class Users extends CoreEntity{

	private static final long serialVersionUID = 2759548343879077045L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
	@Column(name="ID")
	private Long id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="PASS")
	private String pass;
	
	@Column(name="AGE")
	private Integer age;
	
}
