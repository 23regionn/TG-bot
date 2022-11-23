import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './balance-log.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IBalanceLogDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const BalanceLogDetail = (props: IBalanceLogDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { balanceLogEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="balanceLogDetailsHeading">BalanceLog</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{balanceLogEntity.id}</dd>
          <dt>
            <span id="balace">Balace</span>
          </dt>
          <dd>{balanceLogEntity.balace}</dd>
          <dt>
            <span id="userId">User Id</span>
          </dt>
          <dd>{balanceLogEntity.userId}</dd>
          <dt>
            <span id="frostSum">Frost Sum</span>
            <UncontrolledTooltip target="frostSum">сумма заморозки для торгов</UncontrolledTooltip>
          </dt>
          <dd>{balanceLogEntity.frostSum}</dd>
          <dt>
            <span id="dateLastAddBalance">Date Last Add Balance</span>
          </dt>
          <dd>
            {balanceLogEntity.dateLastAddBalance ? (
              <TextFormat value={balanceLogEntity.dateLastAddBalance} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="dateLastMinusFromBalance">Date Last Minus From Balance</span>
          </dt>
          <dd>
            {balanceLogEntity.dateLastMinusFromBalance ? (
              <TextFormat value={balanceLogEntity.dateLastMinusFromBalance} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="date1">Date 1</span>
          </dt>
          <dd>{balanceLogEntity.date1 ? <TextFormat value={balanceLogEntity.date1} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="date2">Date 2</span>
          </dt>
          <dd>{balanceLogEntity.date2 ? <TextFormat value={balanceLogEntity.date2} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="long1">Long 1</span>
          </dt>
          <dd>{balanceLogEntity.long1}</dd>
          <dt>
            <span id="string1">String 1</span>
          </dt>
          <dd>{balanceLogEntity.string1}</dd>
          <dt>
            <span id="boolean1">Boolean 1</span>
          </dt>
          <dd>{balanceLogEntity.boolean1 ? 'true' : 'false'}</dd>
          <dt>Balance</dt>
          <dd>{balanceLogEntity.balance ? balanceLogEntity.balance.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/balance-log" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/balance-log/${balanceLogEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ balanceLog }: IRootState) => ({
  balanceLogEntity: balanceLog.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(BalanceLogDetail);
