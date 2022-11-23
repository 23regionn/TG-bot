import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label, UncontrolledTooltip } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IBalance } from 'app/shared/model/balance.model';
import { getEntities as getBalances } from 'app/entities/balance/balance.reducer';
import { getEntity, updateEntity, createEntity, reset } from './balance-log.reducer';
import { IBalanceLog } from 'app/shared/model/balance-log.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IBalanceLogUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const BalanceLogUpdate = (props: IBalanceLogUpdateProps) => {
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const { balanceLogEntity, balances, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/balance-log');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getBalances();
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
        ...balanceLogEntity,
        ...values,
        balance: balances.find(it => it.id.toString() === values.balanceId.toString()),
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
          <h2 id="gidApp.balanceLog.home.createOrEditLabel" data-cy="BalanceLogCreateUpdateHeading">
            Create or edit a BalanceLog
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : balanceLogEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="balance-log-id">ID</Label>
                  <AvInput id="balance-log-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="balaceLabel" for="balance-log-balace">
                  Balace
                </Label>
                <AvField id="balance-log-balace" data-cy="balace" type="string" className="form-control" name="balace" />
              </AvGroup>
              <AvGroup>
                <Label id="userIdLabel" for="balance-log-userId">
                  User Id
                </Label>
                <AvField id="balance-log-userId" data-cy="userId" type="string" className="form-control" name="userId" />
              </AvGroup>
              <AvGroup>
                <Label id="frostSumLabel" for="balance-log-frostSum">
                  Frost Sum
                </Label>
                <AvField id="balance-log-frostSum" data-cy="frostSum" type="string" className="form-control" name="frostSum" />
                <UncontrolledTooltip target="frostSumLabel">сумма заморозки для торгов</UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="dateLastAddBalanceLabel" for="balance-log-dateLastAddBalance">
                  Date Last Add Balance
                </Label>
                <AvInput
                  id="balance-log-dateLastAddBalance"
                  data-cy="dateLastAddBalance"
                  type="datetime-local"
                  className="form-control"
                  name="dateLastAddBalance"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.balanceLogEntity.dateLastAddBalance)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="dateLastMinusFromBalanceLabel" for="balance-log-dateLastMinusFromBalance">
                  Date Last Minus From Balance
                </Label>
                <AvInput
                  id="balance-log-dateLastMinusFromBalance"
                  data-cy="dateLastMinusFromBalance"
                  type="datetime-local"
                  className="form-control"
                  name="dateLastMinusFromBalance"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.balanceLogEntity.dateLastMinusFromBalance)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="date1Label" for="balance-log-date1">
                  Date 1
                </Label>
                <AvInput
                  id="balance-log-date1"
                  data-cy="date1"
                  type="datetime-local"
                  className="form-control"
                  name="date1"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.balanceLogEntity.date1)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="date2Label" for="balance-log-date2">
                  Date 2
                </Label>
                <AvInput
                  id="balance-log-date2"
                  data-cy="date2"
                  type="datetime-local"
                  className="form-control"
                  name="date2"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.balanceLogEntity.date2)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="long1Label" for="balance-log-long1">
                  Long 1
                </Label>
                <AvField id="balance-log-long1" data-cy="long1" type="string" className="form-control" name="long1" />
              </AvGroup>
              <AvGroup>
                <Label id="string1Label" for="balance-log-string1">
                  String 1
                </Label>
                <AvField id="balance-log-string1" data-cy="string1" type="text" name="string1" />
              </AvGroup>
              <AvGroup check>
                <Label id="boolean1Label">
                  <AvInput id="balance-log-boolean1" data-cy="boolean1" type="checkbox" className="form-check-input" name="boolean1" />
                  Boolean 1
                </Label>
              </AvGroup>
              <AvGroup>
                <Label for="balance-log-balance">Balance</Label>
                <AvInput id="balance-log-balance" data-cy="balance" type="select" className="form-control" name="balanceId">
                  <option value="" key="0" />
                  {balances
                    ? balances.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/balance-log" replace color="info">
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
  balances: storeState.balance.entities,
  balanceLogEntity: storeState.balanceLog.entity,
  loading: storeState.balanceLog.loading,
  updating: storeState.balanceLog.updating,
  updateSuccess: storeState.balanceLog.updateSuccess,
});

const mapDispatchToProps = {
  getBalances,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(BalanceLogUpdate);
