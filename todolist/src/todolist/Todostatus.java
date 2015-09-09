package todolist;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the TODOSTATUS database table.
 * 
 */
@Entity

@Table(name="Todostatus", schema="TESTDB")
@NamedQuery(name="Todostatus.findAll", query="SELECT t FROM Todostatus t")
public class Todostatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String status;

	//bi-directional many-to-one association to Todolist
	@OneToMany(mappedBy="todostatus")
	private List<Todolist> todolists;

	public Todostatus() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Todolist> getTodolists() {
		return this.todolists;
	}

	public void setTodolists(List<Todolist> todolists) {
		this.todolists = todolists;
	}

	public Todolist addTodolist(Todolist todolist) {
		getTodolists().add(todolist);
		todolist.setTodostatus(this);

		return todolist;
	}

	public Todolist removeTodolist(Todolist todolist) {
		getTodolists().remove(todolist);
		todolist.setTodostatus(null);

		return todolist;
	}

}