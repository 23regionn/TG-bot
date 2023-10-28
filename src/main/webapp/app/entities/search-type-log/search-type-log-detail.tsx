import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './search-type-log.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ISearchTypeLogDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const SearchTypeLogDetail = (props: ISearchTypeLogDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { searchTypeLogEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="searchTypeLogDetailsHeading">SearchTypeLog</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{searchTypeLogEntity.id}</dd>
          <dt>
            <span id="chatId">Chat Id</span>
          </dt>
          <dd>{searchTypeLogEntity.chatId}</dd>
          <dt>
            <span id="dateLog">Date Log</span>
          </dt>
          <dd>
            {searchTypeLogEntity.dateLog ? <TextFormat value={searchTypeLogEntity.dateLog} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="inlineSearch">Inline Search</span>
          </dt>
          <dd>{searchTypeLogEntity.inlineSearch ? 'true' : 'false'}</dd>
          <dt>
            <span id="pageSearch">Page Search</span>
          </dt>
          <dd>{searchTypeLogEntity.pageSearch ? 'true' : 'false'}</dd>
          <dt>
            <span id="pageNumber">Page Number</span>
          </dt>
          <dd>{searchTypeLogEntity.pageNumber}</dd>
          <dt>
            <span id="pageNumber">City Id</span>
          </dt>
          <dd>{searchTypeLogEntity.idCity}</dd>
        </dl>
        <Button tag={Link} to="/search-type-log" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/search-type-log/${searchTypeLogEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ searchTypeLog }: IRootState) => ({
  searchTypeLogEntity: searchTypeLog.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(SearchTypeLogDetail);
