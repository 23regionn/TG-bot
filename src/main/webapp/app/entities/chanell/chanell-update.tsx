import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label, UncontrolledTooltip } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ITGUser } from 'app/shared/model/tg-user.model';
import { getEntities as getTGUsers } from 'app/entities/tg-user/tg-user.reducer';
import { ICategory } from 'app/shared/model/category.model';
import { getEntities as getCategories } from 'app/entities/category/category.reducer';
import { getEntity, updateEntity, createEntity, reset } from './chanell.reducer';
import { IChanell } from 'app/shared/model/chanell.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IChanellUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ChanellUpdate = (props: IChanellUpdateProps) => {
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const { chanellEntity, tGUsers, categories, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/chanell');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getTGUsers();
    props.getCategories();
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
        ...chanellEntity,
        ...values,
        tGUser: tGUsers.find(it => it.id.toString() === values.tGUserId.toString()),
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
          <h2 id="gidApp.chanell.home.createOrEditLabel" data-cy="ChanellCreateUpdateHeading">
            Create or edit a Chanell
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : chanellEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="chanell-id">ID</Label>
                  <AvInput id="chanell-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nameLabel" for="chanell-name">
                  Name
                </Label>
                <AvField id="chanell-name" data-cy="name" type="text" name="name" />
              </AvGroup>
              <AvGroup>
                <Label id="linkLabel" for="chanell-link">
                  Link
                </Label>
                <AvField id="chanell-link" data-cy="link" type="text" name="link" />
              </AvGroup>
              <AvGroup>
                <Label id="scoreLabel" for="chanell-score">
                  Score
                </Label>
                <AvField id="chanell-score" data-cy="score" type="string" className="form-control" name="score" />
              </AvGroup>
              <AvGroup>
                <Label id="statusLabel" for="chanell-status">
                  Status
                </Label>
                <AvField id="chanell-status" data-cy="status" type="text" name="status" />
              </AvGroup>
              <AvGroup>
                <Label id="countSubscribersLabel" for="chanell-countSubscribers">
                  Count Subscribers
                </Label>
                <AvField
                  id="chanell-countSubscribers"
                  data-cy="countSubscribers"
                  type="string"
                  className="form-control"
                  name="countSubscribers"
                />
              </AvGroup>
              <AvGroup>
                <Label id="quailityFromAnotherSourcesLabel" for="chanell-quailityFromAnotherSources">
                  Quaility From Another Sources
                </Label>
                <AvField
                  id="chanell-quailityFromAnotherSources"
                  data-cy="quailityFromAnotherSources"
                  type="string"
                  className="form-control"
                  name="quailityFromAnotherSources"
                />
              </AvGroup>
              <AvGroup>
                <Label id="priceDiapozonLabel" for="chanell-priceDiapozon">
                  Price Diapozon
                </Label>
                <AvField id="chanell-priceDiapozon" data-cy="priceDiapozon" type="string" className="form-control" name="priceDiapozon" />
              </AvGroup>
              <AvGroup check>
                <Label id="isModerateLabel">
                  <AvInput id="chanell-isModerate" data-cy="isModerate" type="checkbox" className="form-check-input" name="isModerate" />
                  Is Moderate
                </Label>
              </AvGroup>
              <AvGroup check>
                <Label id="showChanellInTopByCategoryLabel">
                  <AvInput
                    id="chanell-showChanellInTopByCategory"
                    data-cy="showChanellInTopByCategory"
                    type="checkbox"
                    className="form-check-input"
                    name="showChanellInTopByCategory"
                  />
                  Show Chanell In Top By Category
                </Label>
              </AvGroup>
              <AvGroup>
                <Label id="regionLabel" for="chanell-region">
                  Region
                </Label>
                <AvField id="chanell-region" data-cy="region" type="text" name="region" />
              </AvGroup>
              <AvGroup>
                <Label id="cityLabel" for="chanell-city">
                  City
                </Label>
                <AvField id="chanell-city" data-cy="city" type="text" name="city" />
              </AvGroup>
              <AvGroup check>
                <Label id="isDeleteLabel">
                  <AvInput id="chanell-isDelete" data-cy="isDelete" type="checkbox" className="form-check-input" name="isDelete" />
                  Is Delete
                </Label>
                <UncontrolledTooltip target="isDeleteLabel">удаленный</UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="currentDateLabel" for="chanell-currentDate">
                  Current Date
                </Label>
                <AvInput
                  id="chanell-currentDate"
                  data-cy="currentDate"
                  type="datetime-local"
                  className="form-control"
                  name="currentDate"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.chanellEntity.currentDate)}
                />
                <UncontrolledTooltip target="currentDateLabel">текущая дата</UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="date1Label" for="chanell-date1">
                  Date 1
                </Label>
                <AvInput
                  id="chanell-date1"
                  data-cy="date1"
                  type="datetime-local"
                  className="form-control"
                  name="date1"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.chanellEntity.date1)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="date2Label" for="chanell-date2">
                  Date 2
                </Label>
                <AvInput
                  id="chanell-date2"
                  data-cy="date2"
                  type="datetime-local"
                  className="form-control"
                  name="date2"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.chanellEntity.date2)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="long1Label" for="chanell-long1">
                  Long 1
                </Label>
                <AvField id="chanell-long1" data-cy="long1" type="string" className="form-control" name="long1" />
              </AvGroup>
              <AvGroup>
                <Label id="string1Label" for="chanell-string1">
                  String 1
                </Label>
                <AvField id="chanell-string1" data-cy="string1" type="text" name="string1" />
              </AvGroup>
              <AvGroup check>
                <Label id="boolean1Label">
                  <AvInput id="chanell-boolean1" data-cy="boolean1" type="checkbox" className="form-check-input" name="boolean1" />
                  Boolean 1
                </Label>
              </AvGroup>
              <AvGroup>
                <Label for="chanell-tGUser">T G User</Label>
                <AvInput id="chanell-tGUser" data-cy="tGUser" type="select" className="form-control" name="tGUserId">
                  <option value="" key="0" />
                  {tGUsers
                    ? tGUsers.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/chanell" replace color="info">
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
  tGUsers: storeState.tGUser.entities,
  categories: storeState.category.entities,
  chanellEntity: storeState.chanell.entity,
  loading: storeState.chanell.loading,
  updating: storeState.chanell.updating,
  updateSuccess: storeState.chanell.updateSuccess,
});

const mapDispatchToProps = {
  getTGUsers,
  getCategories,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ChanellUpdate);
