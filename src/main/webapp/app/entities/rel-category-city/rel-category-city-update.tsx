import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './rel-category-city.reducer';
import { IRelCategoryCity } from 'app/shared/model/rel-category-city.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IRelCategoryCityUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const RelCategoryCityUpdate = (props: IRelCategoryCityUpdateProps) => {
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const { relCategoryCityEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/rel-category-city');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...relCategoryCityEntity,
        ...values,
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
          <h2 id="gidApp.relCategoryCity.home.createOrEditLabel" data-cy="RelCategoryCityCreateUpdateHeading">
            Create or edit a RelCategoryCity
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : relCategoryCityEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="rel-category-city-id">ID</Label>
                  <AvInput id="rel-category-city-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup check>
                <Label id="isShowLabel">
                  <AvInput id="rel-category-city-isShow" data-cy="isShow" type="checkbox" className="form-check-input" name="isShow" />
                  Is Show
                </Label>
              </AvGroup>
              <AvGroup>
                <Label id="scoreLabel" for="rel-category-city-score">
                  Score
                </Label>
                <AvField id="rel-category-city-score" data-cy="score" type="string" className="form-control" name="score" />
              </AvGroup>
              <AvGroup check>
                <Label id="isFirstLabel">
                  <AvInput id="rel-category-city-isFirst" data-cy="isFirst" type="checkbox" className="form-check-input" name="isFirst" />
                  Is First
                </Label>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/rel-category-city" replace color="info">
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
  relCategoryCityEntity: storeState.relCategoryCity.entity,
  loading: storeState.relCategoryCity.loading,
  updating: storeState.relCategoryCity.updating,
  updateSuccess: storeState.relCategoryCity.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(RelCategoryCityUpdate);
