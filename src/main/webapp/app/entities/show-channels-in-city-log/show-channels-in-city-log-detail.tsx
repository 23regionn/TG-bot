import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './show-channels-in-city-log.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IShowChannelsInCityLogDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ShowChannelsInCityLogDetail = (props: IShowChannelsInCityLogDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { showChannelsInCityLogEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="showChannelsInCityLogDetailsHeading">ShowChannelsInCityLog</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{showChannelsInCityLogEntity.id}</dd>
          <dt>
            <span id="idChannel">Id Channel</span>
          </dt>
          <dd>{showChannelsInCityLogEntity.idChannel}</dd>
          <dt>
            <span id="nameChannel">Name Channel</span>
          </dt>
          <dd>{showChannelsInCityLogEntity.nameChannel}</dd>
          <dt>
            <span id="idCategory">Id Category</span>
          </dt>
          <dd>{showChannelsInCityLogEntity.idCategory}</dd>
          <dt>
            <span id="nameCategory">Name Category</span>
          </dt>
          <dd>{showChannelsInCityLogEntity.nameCategory}</dd>
          <dt>
            <span id="idCity">Id City</span>
          </dt>
          <dd>{showChannelsInCityLogEntity.idCity}</dd>
          <dt>
            <span id="nameCity">Name City</span>
          </dt>
          <dd>{showChannelsInCityLogEntity.nameCity}</dd>
          <dt>
            <span id="isShowChannel">Is Show Channel</span>
          </dt>
          <dd>{showChannelsInCityLogEntity.isShowChannel ? 'true' : 'false'}</dd>
          <dt>
            <span id="scoreChannel">Score Channel</span>
          </dt>
          <dd>{showChannelsInCityLogEntity.scoreChannel}</dd>
          <dt>
            <span id="comment">Comment</span>
          </dt>
          <dd>{showChannelsInCityLogEntity.comment}</dd>
          <dt>
            <span id="dateLog">Date Log</span>
          </dt>
          <dd>
            {showChannelsInCityLogEntity.dateLog ? (
              <TextFormat value={showChannelsInCityLogEntity.dateLog} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
        </dl>
        <Button tag={Link} to="/show-channels-in-city-log" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/show-channels-in-city-log/${showChannelsInCityLogEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ showChannelsInCityLog }: IRootState) => ({
  showChannelsInCityLogEntity: showChannelsInCityLog.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ShowChannelsInCityLogDetail);
