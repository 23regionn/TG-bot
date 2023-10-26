import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './count-channel-click-page-log.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICountChannelClickPageLogDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CountChannelClickPageLogDetail = (props: ICountChannelClickPageLogDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { countChannelClickPageLogEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="countChannelClickPageLogDetailsHeading">CountChannelClickPageLog</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{countChannelClickPageLogEntity.id}</dd>
          <dt>
            <span id="chatId">Chat Id</span>
          </dt>
          <dd>{countChannelClickPageLogEntity.chatId}</dd>
          <dt>
            <span id="dateLog">Date Log</span>
          </dt>
          <dd>
            {countChannelClickPageLogEntity.dateLog ? (
              <TextFormat value={countChannelClickPageLogEntity.dateLog} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="idChannel">Id Channel</span>
          </dt>
          <dd>{countChannelClickPageLogEntity.idChannel}</dd>
          <dt>
            <span id="pageNumber">Page Number</span>
          </dt>
          <dd>{countChannelClickPageLogEntity.pageNumber}</dd>
          <dt>
            <span id="idCategory">Id Category</span>
          </dt>
          <dd>{countChannelClickPageLogEntity.idCategory}</dd>
          <dt>
            <span id="idCity">Id City</span>
          </dt>
          <dd>{countChannelClickPageLogEntity.idCity}</dd>
        </dl>
        <Button tag={Link} to="/count-channel-click-page-log" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/count-channel-click-page-log/${countChannelClickPageLogEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ countChannelClickPageLog }: IRootState) => ({
  countChannelClickPageLogEntity: countChannelClickPageLog.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CountChannelClickPageLogDetail);
