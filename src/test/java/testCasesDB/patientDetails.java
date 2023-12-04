package testCasesDB;

import java.util.Objects;

public class patientDetails {
	private String patient_id;
	private String frist_name;
	private String age;
	private String email;

	public String getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(String patient_id) {
		this.patient_id = patient_id;
	}

	public String getFrist_name() {
		return frist_name;
	}

	public void setFrist_name(String frist_name) {
		this.frist_name = frist_name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "patientDetails [patient_id=" + patient_id + ", frist_name=" + frist_name + ", age=" + age + ", email="
				+ email + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(age, email, frist_name, patient_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		patientDetails other = (patientDetails) obj;
		return Objects.equals(age, other.age) && Objects.equals(email, other.email)
				&& Objects.equals(frist_name, other.frist_name) && Objects.equals(patient_id, other.patient_id);
	}

}
