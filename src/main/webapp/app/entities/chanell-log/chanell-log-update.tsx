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
import { getEntity, updateEntity, createEntity, reset } from './chanell-log.reducer';
import { IChanellLog } from 'app/shared/model/chanell-log.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IChanellLogUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ChanellLogUpdate = (props: IChanellLogUpdateProps) => {
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const { chanellLogEntity, chanells, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/chanell-log');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getChanells();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    values.currentDate = convertDateTimeToServer(values.currentDate);
    values.date1 = convertDateTimeToServer(values.date1);
    values.date2 = convertDateTimeToServer(values.date2);

    if (errors.length === 0) {
      const entity = {
        ...chanellLogEntity,
        ...values,
        chanell: chanells.find(it => it.id.toString() === values.chanellId.toString()),
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
          <h2 id="gidApp.chanellLog.home.createOrEditLabel" data-cy="ChanellLogCreateUpdateHeading">
            Create or edit a ChanellLog
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : chanellLogEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="chanell-log-id">ID</Label>
                  <AvInput id="chanell-log-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nameLabel" for="chanell-log-name">
                  Name
                </Label>
                <AvField id="chanell-log-name" data-cy="name" type="text" name="name" />
              </AvGroup>
              <AvGroup>
                <Label id="linkLabel" for="chanell-log-link">
                  Link
                </Label>
                <AvField id="chanell-log-link" data-cy="link" type="text" name="link" />
              </AvGroup>
              <AvGroup>
                <Label id="scoreLabel" for="chanell-log-score">
                  Score
                </Label>
                <AvField id="chanell-log-score" data-cy="score" type="string" className="form-control" name="score" />
              </AvGroup>
              <AvGroup>
                <Label id="statusLabel" for="chanell-log-status">
                  Status
                </Label>
                <AvField id="chanell-log-status" data-cy="status" type="text" name="status" />
              </AvGroup>
              <AvGroup>
                <Label id="countSubscribersLabel" for="chanell-log-countSubscribers">
                  Count Subscribers
                </Label>
                <AvField
                  id="chanell-log-countSubscribers"
                  data-cy="countSubscribers"
                  type="string"
                  className="form-control"
                  name="countSubscribers"
                />
              </AvGroup>
              <AvGroup>
                <Label id="quailityFromAnotherSourcesLabel" for="chanell-log-quailityFromAnotherSources">
                  Quaility From Another Sources
                </Label>
                <AvField
                  id="chanell-log-quailityFromAnotherSources"
                  data-cy="quailityFromAnotherSources"
                  type="string"
                  className="form-control"
                  name="quailityFromAnotherSources"
                />
              </AvGroup>
              <AvGroup>
                <Label id="priceDiapozonLabel" for="chanell-log-priceDiapozon">
                  Price Diapozon
                </Label>
                <AvField
                  id="chanell-log-priceDiapozon"
                  data-cy="priceDiapozon"
                  type="string"
                  className="form-control"
                  name="priceDiapozon"
                />
              </AvGroup>
              <AvGroup check>
                <Label id="isModerateLabel">
                  <AvInput
                    id="chanell-log-isModerate"
                    data-cy="isModerate"
                    type="checkbox"
                    className="form-check-input"
                    name="isModerate"
                  />
                  Is Moderate
                </Label>
              </AvGroup>
              <AvGroup check>
                <Label id="showChanellInTopByCategoryLabel">
                  <AvInput
                    id="chanell-log-showChanellInTopByCategory"
                    data-cy="showChanellInTopByCategory"
                    type="checkbox"
                    className="form-check-input"
                    name="showChanellInTopByCategory"
                  />
                  Show Chanell In Top By Category
                </Label>
              </AvGroup>
              <AvGroup>
                <Label id="regionLabel" for="chanell-log-region">
                  Region
                </Label>
                <AvField id="chanell-log-region" data-cy="region" type="text" name="region" />
              </AvGroup>
              <AvGroup>
                <Label id="cityLabel" for="chanell-log-city">
                  City
                </Label>
                <AvField id="chanell-log-city" data-cy="city" type="text" name="city" />
              </AvGroup>
              <AvGroup check>
                <Label id="isDeleteLabel">
                  <AvInput id="chanell-log-isDelete" data-cy="isDelete" type="checkbox" className="form-check-input" name="isDelete" />
                  Is Delete
                </Label>
                <UncontrolledTooltip target="isDeleteLabel">удаленный</UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="currentDateLabel" for="chanell-log-currentDate">
                  Current Date
                </Label>
                <AvInput
                  id="chanell-log-currentDate"
                  data-cy="currentDate"
                  type="datetime-local"
                  className="form-control"
                  name="currentDate"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.chanellLogEntity.currentDate)}
                />
                <UncontrolledTooltip target="currentDateLabel">текущая дата</UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="date1Label" for="chanell-log-date1">
                  Date 1
                </Label>
                <AvInput
                  id="chanell-log-date1"
                  data-cy="date1"
                  type="datetime-local"
                  className="form-control"
                  name="date1"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.chanellLogEntity.date1)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="date2Label" for="chanell-log-date2">
                  Date 2
                </Label>
                <AvInput
                  id="chanell-log-date2"
                  data-cy="date2"
                  type="datetime-local"
                  className="form-control"
                  name="date2"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.chanellLogEntity.date2)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="long1Label" for="chanell-log-long1">
                  Long 1
                </Label>
                <AvField id="chanell-log-long1" data-cy="long1" type="string" className="form-control" name="long1" />
              </AvGroup>
              <AvGroup>
                <Label id="string1Label" for="chanell-log-string1">
                  String 1
                </Label>
                <AvField id="chanell-log-string1" data-cy="string1" type="text" name="string1" />
              </AvGroup>
              <AvGroup check>
                <Label id="boolean1Label">
                  <AvInput id="chanell-log-boolean1" data-cy="boolean1" type="checkbox" className="form-check-input" name="boolean1" />
                  Boolean 1
                </Label>
              </AvGroup>
              <AvGroup>
                <Label for="chanell-log-chanell">Chanell</Label>
                <AvInput id="chanell-log-chanell" data-cy="chanell" type="select" className="form-control" name="chanellId">
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
              <Button tag={Link} id="cancel-save" to="/chanell-log" replace color="info">
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
  chanellLogEntity: storeState.chanellLog.entity,
  loading: storeState.chanellLog.loading,
  updating: storeState.chanellLog.updating,
  updateSuccess: storeState.chanellLog.updateSuccess,
});

const mapDispatchToProps = {
  getChanells,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ChanellLogUpdate);
