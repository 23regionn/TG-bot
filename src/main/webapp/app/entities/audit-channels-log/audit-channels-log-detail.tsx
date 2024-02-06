import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './audit-channels-log.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAuditChannelsLogDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const AuditChannelsLogDetail = (props: IAuditChannelsLogDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { auditChannelsLogEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="auditChannelsLogDetailsHeading">AuditChannelsLog</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{auditChannelsLogEntity.id}</dd>
          <dt>
            <span id="dateLog">Date Log</span>
          </dt>
          <dd>
            {auditChannelsLogEntity.dateLog ? (
              <TextFormat value={auditChannelsLogEntity.dateLog} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="comment">Comment</span>
          </dt>
          <dd>{auditChannelsLogEntity.comment}</dd>
          <dt>
            <span id="contacts">Contacts</span>
          </dt>
          <dd>{auditChannelsLogEntity.contacts}</dd>
          <dt>
            <span id="endPublicDate">End Public Date</span>
          </dt>
          <dd>
            {auditChannelsLogEntity.endPublicDate ? (
              <TextFormat value={auditChannelsLogEntity.endPublicDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="idChannel">Id Channel</span>
          </dt>
          <dd>{auditChannelsLogEntity.idChannel}</dd>
          <dt>
            <span id="isModerate">Is Moderate</span>
          </dt>
          <dd>{auditChannelsLogEntity.isModerate ? 'true' : 'false'}</dd>
          <dt>
            <span id="isPay">Is Pay</span>
          </dt>
          <dd>{auditChannelsLogEntity.isPay ? 'true' : 'false'}</dd>
          <dt>
            <span id="lastPayDate">Last Pay Date</span>
          </dt>
          <dd>
            {auditChannelsLogEntity.lastPayDate ? (
              <TextFormat value={auditChannelsLogEntity.lastPayDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="link">Link</span>
          </dt>
          <dd>{auditChannelsLogEntity.link}</dd>
          <dt>
            <span id="nameChannel">Name Channel</span>
          </dt>
          <dd>{auditChannelsLogEntity.nameChannel}</dd>
          <dt>
            <span id="priceForPay">Price For Pay</span>
          </dt>
          <dd>{auditChannelsLogEntity.priceForPay}</dd>
          <dt>
            <span id="startDate">Start Date</span>
          </dt>
          <dd>
            {auditChannelsLogEntity.startDate ? (
              <TextFormat value={auditChannelsLogEntity.startDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="countSubscribers">Count Subscribers</span>
          </dt>
          <dd>{auditChannelsLogEntity.countSubscribers}</dd>
          <dt>
            <span id="countViews">Count Views</span>
          </dt>
          <dd>{auditChannelsLogEntity.countViews}</dd>
          <dt>
            <span id="oldComment">Old Comment</span>
          </dt>
          <dd>{auditChannelsLogEntity.oldComment}</dd>
          <dt>
            <span id="oldContacts">Old Contacts</span>
          </dt>
          <dd>{auditChannelsLogEntity.oldContacts}</dd>
          <dt>
            <span id="oldEndPublicDate">Old End Public Date</span>
          </dt>
          <dd>
            {auditChannelsLogEntity.oldEndPublicDate ? (
              <TextFormat value={auditChannelsLogEntity.oldEndPublicDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="oldIsModerate">Old Is Moderate</span>
          </dt>
          <dd>{auditChannelsLogEntity.oldIsModerate ? 'true' : 'false'}</dd>
          <dt>
            <span id="oldIsPay">Old Is Pay</span>
          </dt>
          <dd>{auditChannelsLogEntity.oldIsPay ? 'true' : 'false'}</dd>
          <dt>
            <span id="oldLastPayDate">Old Last Pay Date</span>
          </dt>
          <dd>
            {auditChannelsLogEntity.oldLastPayDate ? (
              <TextFormat value={auditChannelsLogEntity.oldLastPayDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="oldLink">Old Link</span>
          </dt>
          <dd>{auditChannelsLogEntity.oldLink}</dd>
          <dt>
            <span id="oldNameChannel">Old Name Channel</span>
          </dt>
          <dd>{auditChannelsLogEntity.oldNameChannel}</dd>
          <dt>
            <span id="oldPriceForPay">Old Price For Pay</span>
          </dt>
          <dd>{auditChannelsLogEntity.oldPriceForPay}</dd>
          <dt>
            <span id="oldStartDate">Old Start Date</span>
          </dt>
          <dd>
            {auditChannelsLogEntity.oldStartDate ? (
              <TextFormat value={auditChannelsLogEntity.oldStartDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="oldCountSubscribers">Old Count Subscribers</span>
          </dt>
          <dd>{auditChannelsLogEntity.oldCountSubscribers}</dd>
          <dt>
            <span id="oldCountViews">Old Count Views</span>
          </dt>
          <dd>{auditChannelsLogEntity.oldCountViews}</dd>
        </dl>
        <Button tag={Link} to="/audit-channels-log" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/audit-channels-log/${auditChannelsLogEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ auditChannelsLog }: IRootState) => ({
  auditChannelsLogEntity: auditChannelsLog.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AuditChannelsLogDetail);
