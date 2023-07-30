import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './city.reducer';
import { ICity } from 'app/shared/model/city.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ICityUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CityUpdate = (props: ICityUpdateProps) => {
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const { cityEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/city');
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
    values.dateCreateCity = convertDateTimeToServer(values.dateCreateCity);

    if (errors.length === 0) {
      const entity = {
        ...cityEntity,
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
          <h2 id="gidApp.city.home.createOrEditLabel" data-cy="CityCreateUpdateHeading">
            Create or edit a City
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : cityEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="city-id">ID</Label>
                  <AvInput id="city-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="cityNameLabel" for="city-cityName">
                  City Name
                </Label>
                <AvField id="city-cityName" data-cy="cityName" type="text" name="cityName" />
              </AvGroup>
              <AvGroup>
                <Label id="dateCreateCityLabel" for="city-dateCreateCity">
                  Date Create City
                </Label>
                <AvInput
                  id="city-dateCreateCity"
                  data-cy="dateCreateCity"
                  type="datetime-local"
                  className="form-control"
                  name="dateCreateCity"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.cityEntity.dateCreateCity)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="statusLabel" for="city-status">
                  Status
                </Label>
                <AvField id="city-status" data-cy="status" type="text" name="status" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/city" replace color="info">
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
  cityEntity: storeState.city.entity,
  loading: storeState.city.loading,
  updating: storeState.city.updating,
  updateSuccess: storeState.city.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CityUpdate);
