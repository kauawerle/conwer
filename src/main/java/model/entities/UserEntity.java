package model.entities;

import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "tb_user")
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "cellphone")
	private String cellphone;
	
	@Column(name = "age")
	private int age;
	
	@Column(name = "cpf")
	private String cpf;
	
	@Column(name = "isprofessional")
	private Boolean isprofessional;
	
	@Column(name = "work_hour")
	private LocalTime work_hour;
	
	@Column(name = "speciality")
	private String speciality;
	
	@ManyToOne
	@JoinColumn(name = "id_user", referencedColumnName = "id_user")
	private ScheduleEntity id_user;
	
	@ManyToOne
	@JoinColumn(name = "id_schedule", referencedColumnName = "id")
    private ScheduleEntity schedule;
	
	@ManyToOne
	@JoinColumn(name = "id_appointment", referencedColumnName = "id")
    private AppointmentEntity appointment;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "address_id") // FK em users
    private AddressEntity address;

    public AddressEntity getAddress() { return address; }
    public void setAddress(AddressEntity address) { this.address = address; }
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Boolean getIsprofessional() {
		return isprofessional;
	}

	public void setIsprofessional(Boolean isprofessional) {
		this.isprofessional = isprofessional;
	}

	public LocalTime getWork_hour() {
		return work_hour;
	}

	public void setWork_hour(LocalTime work_hour) {
		this.work_hour = work_hour;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public AppointmentEntity getAppointment() {
		return appointment;
	}

	public void setAppointment(AppointmentEntity appointment) {
		this.appointment = appointment;
	}

	public ScheduleEntity getSchedule() {
		return schedule;
	}

	public void setSchedule(ScheduleEntity schedule) {
		this.schedule = schedule;
	}

	public UserEntity() {}
	public UserEntity( String name, String email, String password, String cellphone, int age, String cpf,
			Boolean isprofessional, LocalTime work_hour, String speciality) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.cellphone = cellphone;
		this.age = age;
		this.cpf = cpf;
		this.isprofessional = isprofessional;
		this.work_hour = work_hour;
		this.speciality = speciality;
	}
	
}
