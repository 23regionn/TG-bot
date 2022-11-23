import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label, UncontrolledTooltip } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IChanell } from 'app/shared/model/chanell.model';
import { getEntities as getChanells } from 'app/entities/chanell/chanell.reducer';
import { ILinksByCategoryInTop } from 'app/shared/model/links-by-category-in-top.model';
import { getEntities as getLinksByCategoryInTops } from 'app/entities/links-by-category-in-top/links-by-category-in-top.reducer';
import { getEntity, updateEntity, createEntity, reset } from './category.reducer';
import { ICategory } from 'app/shared/model/category.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ICategoryUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CategoryUpdate = (props: ICategoryUpdateProps) => {
  const [idschanellId, setIdschanellId] = useState([]);
  const [idslinksByCategoryInTopId, setIdslinksByCategoryInTopId] = useState([]);
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const { categoryEntity, chanells, linksByCategoryInTops, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/category');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getChanells();
    props.getLinksByCategoryInTops();
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
        ...categoryEntity,
        ...values,
        chanellIds: mapIdList(values.chanellIds),
        linksByCategoryInTopIds: mapIdList(values.linksByCategoryInTopIds),
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
          <h2 id="gidApp.category.home.createOrEditLabel" data-cy="CategoryCreateUpdateHeading">
            Create or edit a Category
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : categoryEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="category-id">ID</Label>
                  <AvInput id="category-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nameLabel" for="category-name">
                  Name
                </Label>
                <AvField id="category-name" data-cy="name" type="text" name="name" />
              </AvGroup>
              <AvGroup>
                <Label id="countChanellInCategoryLabel" for="category-countChanellInCategory">
                  Count Chanell In Category
                </Label>
                <AvField
                  id="category-countChanellInCategory"
                  data-cy="countChanellInCategory"
                  type="string"
                  className="form-control"
                  name="countChanellInCategory"
                />
              </AvGroup>
              <AvGroup check>
                <Label id="isDeleteLabel">
                  <AvInput id="category-isDelete" data-cy="isDelete" type="checkbox" className="form-check-input" name="isDelete" />
                  Is Delete
                </Label>
                <UncontrolledTooltip target="isDeleteLabel">удаленный</UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="date1Label" for="category-date1">
                  Date 1
                </Label>
                <AvInput
                  id="category-date1"
                  data-cy="date1"
                  type="datetime-local"
                  className="form-control"
                  name="date1"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.categoryEntity.date1)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="date2Label" for="category-date2">
                  Date 2
                </Label>
                <AvInput
                  id="category-date2"
                  data-cy="date2"
                  type="datetime-local"
                  className="form-control"
                  name="date2"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.categoryEntity.date2)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="long1Label" for="category-long1">
                  Long 1
                </Label>
                <AvField id="category-long1" data-cy="long1" type="string" className="form-control" name="long1" />
              </AvGroup>
              <AvGroup>
                <Label id="string1Label" for="category-string1">
                  String 1
                </Label>
                <AvField id="category-string1" data-cy="string1" type="text" name="string1" />
              </AvGroup>
              <AvGroup check>
                <Label id="boolean1Label">
                  <AvInput id="category-boolean1" data-cy="boolean1" type="checkbox" className="form-check-input" name="boolean1" />
                  Boolean 1
                </Label>
              </AvGroup>
              <AvGroup>
                <Label for="category-chanellId">Chanell Id</Label>
                <AvInput
                  id="category-chanellId"
                  data-cy="chanellId"
                  type="select"
                  multiple
                  className="form-control"
                  name="chanellIds"
                  value={!isNew && categoryEntity.chanellIds && categoryEntity.chanellIds.map(e => e.id)}
                >
                  <option value="" key="0" />
                  {chanells
                    ? chanells.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="category-linksByCategoryInTopId">Links By Category In Top Id</Label>
                <AvInput
                  id="category-linksByCategoryInTopId"
                  data-cy="linksByCategoryInTopId"
                  type="select"
                  multiple
                  className="form-control"
                  name="linksByCategoryInTopIds"
                  value={!isNew && categoryEntity.linksByCategoryInTopIds && categoryEntity.linksByCategoryInTopIds.map(e => e.id)}
                >
                  <option value="" key="0" />
                  {linksByCategoryInTops
                    ? linksByCategoryInTops.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/category" replace color="info">
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
  chanells: storeState.chanell.entities,
  linksByCategoryInTops: storeState.linksByCategoryInTop.entities,
  categoryEntity: storeState.category.entity,
  loading: storeState.category.loading,
  updating: storeState.category.updating,
  updateSuccess: storeState.category.updateSuccess,
});

const mapDispatchToProps = {
  getChanells,
  getLinksByCategoryInTops,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CategoryUpdate);
