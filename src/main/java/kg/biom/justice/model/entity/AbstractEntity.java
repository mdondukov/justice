package kg.biom.justice.model.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Абстрактный класс от которого наследуются сущности
 */
@Data
@MappedSuperclass
@EqualsAndHashCode
@NoArgsConstructor
public abstract class AbstractEntity implements Serializable {
}
