package com.innova.backend.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ForeignKey;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.innova.backend.model.Country;

@Entity(name = "Team")
@Table(name = "tbl_teams")
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message="Name required")
	@Length(min=4, max=24, message="Length between 4 and 24")
	private String name;
	
	@NotNull(message="Country required")
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_COUNTRY"), name = "countryid", referencedColumnName = "id")
	private Country country;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_TEAM_RIVAL"), name = "teamrivalid", referencedColumnName = "id")
	@JsonIgnore
	private Team teamrival;

    @Column(name="shieldfilename")
    private String shieldfilename;
    
    @Lob
    @Column(name="shieldfile")
    private String shieldfile;

    // getters and setters
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Team getTeamrival() {
		return teamrival;
	}

	public void setTeamrival(Team teamrival) {
		this.teamrival = teamrival;
	}

	public String getShieldfilename() {
		return shieldfilename;
	}

	public void setShieldfilename(String shieldfilename) {
		this.shieldfilename = shieldfilename;
	}

	public String getShieldfile() {
		return shieldfile;
	}

	public void setShieldfile(String shieldfile) {
		this.shieldfile = shieldfile;
	}

	@Override
	public String toString() {
		return "Team [id=" + id + ", name=" + name + ", country=" + country + ", teamrival=" + teamrival
				+ ", shieldfilename=" + shieldfilename + ", shieldfile=" + shieldfile + "]";
	}

	public Team(Long id, String name, Country country, Team teamrival, String shieldfilename, String shieldfile) {
		super();
		this.id = id;
		this.name = name;
		this.country = country;
		this.teamrival = teamrival;
		this.shieldfilename = shieldfilename;
		this.shieldfile = shieldfile;
	}
    
	public Team() {
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((shieldfile == null) ? 0 : shieldfile.hashCode());
		result = prime * result + ((shieldfilename == null) ? 0 : shieldfilename.hashCode());
		result = prime * result + ((teamrival == null) ? 0 : teamrival.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Team other = (Team) obj;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (shieldfile == null) {
			if (other.shieldfile != null)
				return false;
		} else if (!shieldfile.equals(other.shieldfile))
			return false;
		if (shieldfilename == null) {
			if (other.shieldfilename != null)
				return false;
		} else if (!shieldfilename.equals(other.shieldfilename))
			return false;
		if (teamrival == null) {
			if (other.teamrival != null)
				return false;
		} else if (!teamrival.equals(other.teamrival))
			return false;
		return true;
	}
	
	
	
}
