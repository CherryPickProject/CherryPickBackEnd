package charrypick.charrypickapp.basetest.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@Getter
@MappedSuperclass
public class BaseEntity implements Serializable {

	@CreatedDate
	@Column(updatable = false,name = "created_at")
	private LocalDateTime createdAt;

	@LastModifiedDate
	@Column(name = "update_at")
	private LocalDateTime updateAt;

//	@Setter
//	@Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT true")
//	private Boolean isEnable = true;

	@PrePersist
	public void prePersist(){
		LocalDateTime now = LocalDateTime.now();
		createdAt = now;
		updateAt = now;
	}

	@PreUpdate
	public void preUpdate(){
		updateAt = LocalDateTime.now();
	}

}