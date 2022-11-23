import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './config-table.reducer';
import { IConfigTable } from 'app/shared/model/config-table.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IConfigTableUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ConfigTableUpdate = (props: IConfigTableUpdateProps) => {
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const { configTableEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/config-table');
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
    values.dateOne = convertDateTimeToServer(values.dateOne);
    values.dateTwo = convertDateTimeToServer(values.dateTwo);
    values.date1 = convertDateTimeToServer(values.date1);
    values.date2 = convertDateTimeToServer(values.date2);

    if (errors.length === 0) {
      const entity = {
        ...configTableEntity,
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
          <h2 id="gidApp.configTable.home.createOrEditLabel" data-cy="ConfigTableCreateUpdateHeading">
            Create or edit a ConfigTable
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : configTableEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="config-table-id">ID</Label>
                  <AvInput id="config-table-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="dateOneLabel" for="config-table-dateOne">
                  Date One
                </Label>
                <AvInput
                  id="config-table-dateOne"
                  data-cy="dateOne"
                  type="datetime-local"
                  className="form-control"
                  name="dateOne"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.configTableEntity.dateOne)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="dateTwoLabel" for="config-table-dateTwo">
                  Date Two
                </Label>
                <AvInput
                  id="config-table-dateTwo"
                  data-cy="dateTwo"
                  type="datetime-local"
                  className="form-control"
                  name="dateTwo"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.configTableEntity.dateTwo)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="longOneLabel" for="config-table-longOne">
                  Long One
                </Label>
                <AvField id="config-table-longOne" data-cy="longOne" type="string" className="form-control" name="longOne" />
              </AvGroup>
              <AvGroup>
                <Label id="stringOneLabel" for="config-table-stringOne">
                  String One
                </Label>
                <AvField id="config-table-stringOne" data-cy="stringOne" type="text" name="stringOne" />
              </AvGroup>
              <AvGroup check>
                <Label id="booleanOneLabel">
                  <AvInput
                    id="config-table-booleanOne"
                    data-cy="booleanOne"
                    type="checkbox"
                    className="form-check-input"
                    name="booleanOne"
                  />
                  Boolean One
                </Label>
              </AvGroup>
              <AvGroup check>
                <Label id="booleanTwoLabel">
                  <AvInput
                    id="config-table-booleanTwo"
                    data-cy="booleanTwo"
                    type="checkbox"
                    className="form-check-input"
                    name="booleanTwo"
                  />
                  Boolean Two
                </Label>
              </AvGroup>
              <AvGroup>
                <Label id="date1Label" for="config-table-date1">
                  Date 1
                </Label>
                <AvInput
                  id="config-table-date1"
                  data-cy="date1"
                  type="datetime-local"
                  className="form-control"
                  name="date1"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.configTableEntity.date1)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="date2Label" for="config-table-date2">
                  Date 2
                </Label>
                <AvInput
                  id="config-table-date2"
                  data-cy="date2"
                  type="datetime-local"
                  className="form-control"
                  name="date2"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.configTableEntity.date2)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="long1Label" for="config-table-long1">
                  Long 1
                </Label>
                <AvField id="config-table-long1" data-cy="long1" type="string" className="form-control" name="long1" />
              </AvGroup>
              <AvGroup>
                <Label id="string1Label" for="config-table-string1">
                  String 1
                </Label>
                <AvField id="config-table-string1" data-cy="string1" type="text" name="string1" />
              </AvGroup>
              <AvGroup check>
                <Label id="boolean1Label">
                  <AvInput id="config-table-boolean1" data-cy="boolean1" type="checkbox" className="form-check-input" name="boolean1" />
                  Boolean 1
                </Label>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/config-table" replace color="info">
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
  configTableEntity: storeState.configTable.entity,
  loading: storeState.configTable.loading,
  updating: storeState.configTable.updating,
  updateSuccess: storeState.configTable.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ConfigTableUpdate);
