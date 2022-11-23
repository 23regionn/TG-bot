import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label, UncontrolledTooltip } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ICategory } from 'app/shared/model/category.model';
import { getEntities as getCategories } from 'app/entities/category/category.reducer';
import { getEntity, updateEntity, createEntity, reset } from './category-log.reducer';
import { ICategoryLog } from 'app/shared/model/category-log.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ICategoryLogUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CategoryLogUpdate = (props: ICategoryLogUpdateProps) => {
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const { categoryLogEntity, categories, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/category-log');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getCategories();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    values.date1 = convertDateTimeToServer(values.date1);
    values.date2 = convertDateTimeToServer(values.date2);

    if (errors.length === 0) {
      const entity = {
        ...categoryLogEntity,
        ...values,
        category: categories.find(it => it.id.toString() === values.categoryId.toString()),
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="gidApp.categoryLog.home.createOrEditLabel" data-cy="CategoryLogCreateUpdateHeading">
            Create or edit a CategoryLog
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : categoryLogEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="category-log-id">ID</Label>
                  <AvInput id="category-log-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nameLabel" for="category-log-name">
                  Name
                </Label>
                <AvField id="category-log-name" data-cy="name" type="text" name="name" />
              </AvGroup>
              <AvGroup>
                <Label id="countChanellInCategoryLabel" for="category-log-countChanellInCategory">
                  Count Chanell In Category
                </Label>
                <AvField
                  id="category-log-countChanellInCategory"
                  data-cy="countChanellInCategory"
                  type="string"
                  className="form-control"
                  name="countChanellInCategory"
                />
              </AvGroup>
              <AvGroup check>
                <Label id="isDeleteLabel">
                  <AvInput id="category-log-isDelete" data-cy="isDelete" type="checkbox" className="form-check-input" name="isDelete" />
                  Is Delete
                </Label>
                <UncontrolledTooltip target="isDeleteLabel">удаленный</UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="date1Label" for="category-log-date1">
                  Date 1
                </Label>
                <AvInput
                  id="category-log-date1"
                  data-cy="date1"
                  type="datetime-local"
                  className="form-control"
                  name="date1"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.categoryLogEntity.date1)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="date2Label" for="category-log-date2">
                  Date 2
                </Label>
                <AvInput
                  id="category-log-date2"
                  data-cy="date2"
                  type="datetime-local"
                  className="form-control"
                  name="date2"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.categoryLogEntity.date2)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="long1Label" for="category-log-long1">
                  Long 1
                </Label>
                <AvField id="category-log-long1" data-cy="long1" type="string" className="form-control" name="long1" />
              </AvGroup>
              <AvGroup>
                <Label id="string1Label" for="category-log-string1">
                  String 1
                </Label>
                <AvField id="category-log-string1" data-cy="string1" type="text" name="string1" />
              </AvGroup>
              <AvGroup check>
                <Label id="boolean1Label">
                  <AvInput id="category-log-boolean1" data-cy="boolean1" type="checkbox" className="form-check-input" name="boolean1" />
                  Boolean 1
                </Label>
              </AvGroup>
              <AvGroup>
                <Label for="category-log-category">Category</Label>
                <AvInput id="category-log-category" data-cy="category" type="select" className="form-control" name="categoryId">
                  <option value="" key="0" />
                  {categories
                    ? categories.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/category-log" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">Back</span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp; Save
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  categories: storeState.category.entities,
  categoryLogEntity: storeState.categoryLog.entity,
  loading: storeState.categoryLog.loading,
  updating: storeState.categoryLog.updating,
  updateSuccess: storeState.categoryLog.updateSuccess,
});

const mapDispatchToProps = {
  getCategories,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CategoryLogUpdate);
