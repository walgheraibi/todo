package todolist;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the TODOUSERS database table.
 * 
 */
@Entity
@Table(name="TODOUSERS", schema="TESTDB")
@NamedQuery(name="Todouser.findAll", query="SELECT t FROM Todouser t")
public class Todouser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String email;

	private String password;

	private String username;

	//bi-directional many-to-one association to Todolist
	@OneToMany(mappedBy="todouser")
	private List<Todolist> todolists;

	public Todouser() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Todolist> getTodolists() {
		return this.todolists;
	}

	public void setTodolists(List<Todolist> todolists) {
		this.todolists = todolists;
	}

	public Todolist addTodolist(Todolist todolist) {
		getTodolists().add(todolist);
		todolist.setTodouser(this);

		return todolist;
	}

	public Todolist removeTodolist(Todolist todolist) {
		getTodolists().remove(todolist);
		todolist.setTodouser(null);

		return todolist;
	}

}