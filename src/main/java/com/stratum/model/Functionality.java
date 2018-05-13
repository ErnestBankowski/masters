package com.stratum.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="functionality")
public class Functionality {
	
	@Id	
	@Column(name="functionality_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name="sprint")
	private Sprint sprint;
	
	@Column(name="creation_date")
	private Date creationDate;
	
	@ManyToOne
	@JoinColumn(name="creator")
	private User creator;
	
	@ManyToOne
	@JoinColumn(name="responsible_developer")
	private User responsibleDeveloper;
	
	@ManyToOne
	@JoinColumn(name="responsible_architect")
	private User responsibleArchitect;
	
	@ManyToOne
	@JoinColumn(name="responsible_tester")
	private User responsibleTester;
	
	@Column(name="name")
	private String name;
	
	@Column(name="state")
	private String state;
	
	@ManyToOne
	@JoinColumn(name="specification")
	private Specification specification;
	
	@ManyToOne
	@JoinColumn(name="useCase")
	private UseCase useCase;
	
	public Functionality() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Sprint getSprint() {
		return sprint;
	}

	public void setSprint(Sprint sprint) {
		this.sprint = sprint;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public User getResponsibleDeveloper() {
		return responsibleDeveloper;
	}

	public void setResponsibleDeveloper(User responsibleDeveloper) {
		this.responsibleDeveloper = responsibleDeveloper;
	}

	public User getResponsibleArchitect() {
		return responsibleArchitect;
	}

	public void setResponsibleArchitect(User responsibleArchitect) {
		this.responsibleArchitect = responsibleArchitect;
	}

	public User getResponsibleTester() {
		return responsibleTester;
	}

	public void setResponsibleTester(User responsibleTester) {
		this.responsibleTester = responsibleTester;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Specification getSpecification() {
		return specification;
	}

	public void setSpecification(Specification specification) {
		this.specification = specification;
	}

	public UseCase getUseCase() {
		return useCase;
	}

	public void setUseCase(UseCase useCase) {
		this.useCase = useCase;
	}
	
	public Functionality(FunctionalityBuilder builder) {
		this.sprint = builder.sprint;
		this.creationDate = builder.creationDate;
		this.creator = builder.creator;
		this.responsibleArchitect = builder.responsibleArchitect;
		this.responsibleDeveloper = builder.responsibleDeveloper;
		this.responsibleTester = builder.responsibleTester;
		this.state = builder.state;
		this.name = builder.name;
		this.specification = builder.specification;
		this.useCase = builder.useCase;
	}
	
	public static class FunctionalityBuilder {
		
		private Sprint sprint;
		private Date creationDate;
		private User creator;
		private User responsibleDeveloper;
		private User responsibleArchitect;
		private User responsibleTester;
		private String name;
		private String state;
		private Specification specification;
		private UseCase useCase;
		
		public FunctionalityBuilder() {

		}
		
		public FunctionalityBuilder sprint(Sprint sprint) {
			this.sprint = sprint;
			return this;
		}
		
		public FunctionalityBuilder creationDate(Date creationDate) {
			this.creationDate = creationDate;
			return this;
		}
		
		public FunctionalityBuilder creator(User creator) {
			this.creator = creator;
			return this;
		}
		
		public FunctionalityBuilder responsibleDeveloper(User responsibleDeveloper) {
			this.responsibleDeveloper = responsibleDeveloper;
			return this;
		}
		
		public FunctionalityBuilder responsibleArchitect(User responsibleArchitect) {
			this.responsibleArchitect = responsibleArchitect;
			return this;
		}
		
		public FunctionalityBuilder responsibleTester(User responsibleTester) {
			this.responsibleTester = responsibleTester;
			return this;
		}
		
		public FunctionalityBuilder name(String name) {
			this.name = name;
			return this;
		}
		
		public FunctionalityBuilder state(String state) {
			this.state = state;
			return this;
		}
		
		public FunctionalityBuilder specification(Specification specification) {
			this.specification = specification;
			return this;
		}
		
		public FunctionalityBuilder useCase(UseCase useCase) {
			this.useCase = useCase;
			return this;
		}
		
		public Functionality build() {
			return new Functionality(this);
		}		
	}
	
	public static enum State{
		NEW {
			public String toString() {
		          return "NEW";
		      }
		},
		SPECIFIED {
			public String toString() {
		          return "SPECIFIED";
		      }
		},
		ASSIGNED {
			public String toString() {
		          return "ASSIGNED";
		      }
		},
		IN_DEVELOPMENT {
			public String toString() {
		          return "IN DEVELOPMENT";
		      }
		},
		UNDER_REVIEW {
			public String toString() {
		          return "UNDER REVIEW";
		      }
		},
		IN_TESTING {
			public String toString() {
		          return "IN TESTING";
		      }
		},
		VALIDATED {
			public String toString() {
		          return "VALIDATED";
		      }
		},
		COMPLETED {
			public String toString() {
		          return "COMPLETED";
		      }
		},
		WITHDRAWN {
			public String toString() {
		          return "WITHDRAWN";
		      }
		}
	}
	
	
	
	

}
