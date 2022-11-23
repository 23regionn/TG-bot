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
import { getEntity, updateEntity, createEntity, reset } from './balance.reducer';
import { IBalance } from 'app/shared/model/balance.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IBalanceUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const BalanceUpdate = (props: IBalanceUpdateProps) => {
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const { balanceEntity, tGUsers, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/balance');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getTGUsers();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    values.dateLastAddBalance = convertDateTimeToServer(values.dateLastAddBalance);
    values.dateLastMinusFromBalance = convertDateTimeToServer(values.dateLastMinusFromBalance);
    values.date1 = convertDateTimeToServer(values.date1);
    values.date2 = convertDateTimeToServer(values.date2);

    if (errors.length === 0) {
      const entity = {
        ...balanceEntity,
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
          <h2 id="gidApp.balance.home.createOrEditLabel" data-cy="BalanceCreateUpdateHeading">
            Create or edit a Balance
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : balanceEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="balance-id">ID</Label>
                  <AvInput id="balance-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="balaceLabel" for="balance-balace">
                  Balace
                </Label>
                <AvField id="balance-balace" data-cy="balace" type="string" className="form-control" name="balace" />
              </AvGroup>
              <AvGroup>
                <Label id="userIdLabel" for="balance-userId">
                  User Id
                </Label>
                <AvField id="balance-userId" data-cy="userId" type="string" className="form-control" name="userId" />
              </AvGroup>
              <AvGroup>
                <Label id="frostSumLabel" for="balance-frostSum">
                  Frost Sum
                </Label>
                <AvField id="balance-frostSum" data-cy="frostSum" type="string" className="form-control" name="frostSum" />
                <UncontrolledTooltip target="frostSumLabel">сумма заморозки для торгов</UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="dateLastAddBalanceLabel" for="balance-dateLastAddBalance">
                  Date Last Add Balance
                </Label>
                <AvInput
                  id="balance-dateLastAddBalance"
                  data-cy="dateLastAddBalance"
                  type="datetime-local"
                  className="form-control"
                  name="dateLastAddBalance"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.balanceEntity.dateLastAddBalance)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="dateLastMinusFromBalanceLabel" for="balance-dateLastMinusFromBalance">
                  Date Last Minus From Balance
                </Label>
                <AvInput
                  id="balance-dateLastMinusFromBalance"
                  data-cy="dateLastMinusFromBalance"
                  type="datetime-local"
                  className="form-control"
                  name="dateLastMinusFromBalance"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.balanceEntity.dateLastMinusFromBalance)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="date1Label" for="balance-date1">
                  Date 1
                </Label>
                <AvInput
                  id="balance-date1"
                  data-cy="date1"
                  type="datetime-local"
                  className="form-control"
                  name="date1"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.balanceEntity.date1)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="date2Label" for="balance-date2">
                  Date 2
                </Label>
                <AvInput
                  id="balance-date2"
                  data-cy="date2"
                  type="datetime-local"
                  className="form-control"
                  name="date2"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.balanceEntity.date2)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="long1Label" for="balance-long1">
                  Long 1
                </Label>
                <AvField id="balance-long1" data-cy="long1" type="string" className="form-control" name="long1" />
              </AvGroup>
              <AvGroup>
                <Label id="string1Label" for="balance-string1">
                  String 1
                </Label>
                <AvField id="balance-string1" data-cy="string1" type="text" name="string1" />
              </AvGroup>
              <AvGroup check>
                <Label id="boolean1Label">
                  <AvInput id="balance-boolean1" data-cy="boolean1" type="checkbox" className="form-check-input" name="boolean1" />
                  Boolean 1
                </Label>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/balance" replace color="info">
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
  balanceEntity: storeState.balance.entity,
  loading: storeState.balance.loading,
  updating: storeState.balance.updating,
  updateSuccess: storeState.balance.updateSuccess,
});

const mapDispatchToProps = {
  getTGUsers,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(BalanceUpdate);
