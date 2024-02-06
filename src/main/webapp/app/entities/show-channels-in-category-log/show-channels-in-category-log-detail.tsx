import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './show-channels-in-category-log.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IShowChannelsInCategoryLogDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ShowChannelsInCategoryLogDetail = (props: IShowChannelsInCategoryLogDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { showChannelsInCategoryLogEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="showChannelsInCategoryLogDetailsHeading">ShowChannelsInCategoryLog</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{showChannelsInCategoryLogEntity.id}</dd>
          <dt>
            <span id="idChannel">Id Channel</span>
          </dt>
          <dd>{showChannelsInCategoryLogEntity.idChannel}</dd>
          <dt>
            <span id="nameChannel">Name Channel</span>
          </dt>
          <dd>{showChannelsInCategoryLogEntity.nameChannel}</dd>
          <dt>
            <span id="idCategory">Id Category</span>
          </dt>
          <dd>{showChannelsInCategoryLogEntity.idCategory}</dd>
          <dt>
            <span id="nameCategory">Name Category</span>
          </dt>
          <dd>{showChannelsInCategoryLogEntity.nameCategory}</dd>
          <dt>
            <span id="isShowChannel">Is Show Channel</span>
          </dt>
          <dd>{showChannelsInCategoryLogEntity.isShowChannel ? 'true' : 'false'}</dd>
          <dt>
            <span id="scoreChannel">Score Channel</span>
          </dt>
          <dd>{showChannelsInCategoryLogEntity.scoreChannel}</dd>
          <dt>
            <span id="comment">Comment</span>
          </dt>
          <dd>{showChannelsInCategoryLogEntity.comment}</dd>
          <dt>
            <span id="oldIsShowChannel">Old Is Show Channel</span>
          </dt>
          <dd>{showChannelsInCategoryLogEntity.oldIsShowChannel ? 'true' : 'false'}</dd>
          <dt>
            <span id="oldScoreChannel">Old Score Channel</span>
          </dt>
          <dd>{showChannelsInCategoryLogEntity.oldScoreChannel}</dd>
          <dt>
            <span id="oldComment">Old Comment</span>
          </dt>
          <dd>{showChannelsInCategoryLogEntity.oldComment}</dd>
          <dt>
            <span id="dateLog">Date Log</span>
          </dt>
          <dd>
            {showChannelsInCategoryLogEntity.dateLog ? (
              <TextFormat value={showChannelsInCategoryLogEntity.dateLog} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
        </dl>
        <Button tag={Link} to="/show-channels-in-category-log" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/show-channels-in-category-log/${showChannelsInCategoryLogEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ showChannelsInCategoryLog }: IRootState) => ({
  showChannelsInCategoryLogEntity: showChannelsInCategoryLog.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ShowChannelsInCategoryLogDetail);
